package com.al.project.SysCo.Service;

import com.al.project.SysCo.Model.Data;
import com.al.project.SysCo.Model.Publisher;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java .sql.*;


@SpringBootApplication
public class RabbitMQService {

    @Autowired
   private static DataService dataService;

    public static void main(String[] args)  throws Exception{

        System.out.println(" START !\n To stop, type 'exit'");

        final String EXCHANGE_NAME = "RPiTopics";

        ConnectionFactory factory = new ConnectionFactory();

        final String hostIP = "193.48.57.166";
        final String username = "ima2a5-4fun";
        final String password = "glopglop";

        factory.setHost(hostIP);
        factory.setUsername(username);
        factory.setPassword(password);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "Rpi.DataBase.Room.*");
        channel.queueBind(queueName, EXCHANGE_NAME, "Rpi.User.Room.*");

        System.out.println(" [*] Waiting for messages. To exit type 'exit'");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String response = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + response + "'");


            if(delivery.getEnvelope().getRoutingKey().contains("Rpi.User.Room.")){
                //se connecter au controller
                //Voir avec MAlick


            }
            else{
                //sauvegarder dans bdd

                try {

                 //   DataService dataService= new DataService();
                    Data data= new Data().jsonstringToData(response);
                    System.out.println(data);
                    dataService.saveData(data);

                    //DataAPI.saveData(new Data().jsonstringToData(response));
                    //Data data = new Data().jsonstringToData(response);

                    //dataService.saveData(data);
                }


                 catch (Exception ex){
                    System.out.println(ex);
                }


            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }



    public static void GetRealTimeTopics(String idRpi, String request){

        try {
            String EXCHANGE_NAME = "RpiTopics";
            Publisher publisher = new Publisher();

            if(Objects.nonNull(idRpi)) {                                                                              // Avoids filling DB with values when sensor if off
                publisher.publish(EXCHANGE_NAME,"User.RPi.Room."+idRpi,request);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    // JDBC driver name and database URL

    /*static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://192.168.100.174/db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

*/

    /* Config MariaDB */
        /*java.sql.Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/db", "root", "glopglop");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = ((java.sql.Connection) conn).createStatement();


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try


*/


}
