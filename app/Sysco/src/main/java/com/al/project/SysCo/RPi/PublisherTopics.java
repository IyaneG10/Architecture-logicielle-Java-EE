package com.al.project.SysCo.RPi;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TopicPublisher;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;


public class Publisher {

    private static final Log log = LogFactory.getLog(StockQuoter.class);

    private List<String> stocks = new ArrayList<String>();
    private Map<String, Double> lastPrice = new HashMap<String, Double>();

    {
        stocks.add("AAPL");
        stocks.add("GD");
        stocks.add("BRK.B");

        lastPrice.put("AAPL", 494.64);
        lastPrice.put("GD", 86.74);
        lastPrice.put("BRK.B", 113.59);
    }

    @Autowired
    JmsTemplate jmsTemplate;

    @Bean
    ConnectionFactory connectionFactory() {
        return new RMQConnectionFactory();
    }

    @Scheduled(fixedRate = 5000L) // every 5 seconds
    public void publishQuote() {
        // Pick a random stock symbol
        Collections.shuffle(stocks);
        final String symbol = stocks.get(0);

        // Toss a coin and decide if the price goes...
        if (RandomUtils.nextBoolean()) {
        // ...up by a random 0-10%
            lastPrice.put(symbol, new Double(Math.round(lastPrice.get(symbol) * (1 + RandomUtils.nextInt(10)/100.0) * 100) / 100));
        } else {
        // ...or down by a similar random amount
            lastPrice.put(symbol, new Double(Math.round(lastPrice.get(symbol) * (1 - RandomUtils.nextInt(10)/100.0) * 100) / 100));
        }

        // Log new price locally
        log.info("Quote..." + symbol + " is now " + lastPrice.get(symbol));

        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage("Quote..." + symbol + " is now " + lastPrice.get(symbol));
            }
        };

        jmsTemplate.send("rabbit-trader-channel", messageCreator);
    }
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(StockQuoter.class, args);
        log.info("connectionFactory => " + ctx.getBean("connectionFactory"));
    }
}