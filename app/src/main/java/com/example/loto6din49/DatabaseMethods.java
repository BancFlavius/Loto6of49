package com.example.loto6din49;

import android.os.StrictMode;

import org.apache.commons.lang.RandomStringUtils;

import java.sql.*;
import java.util.*;

public class DatabaseMethods {

    public static void stricMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }



    public static void insertTicket(String fullname, String phone, long n01, long n02, long n03, long n04, long n05, long n06){
        try {
            stricMode();
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO tickets(fullname, n01, n02, n03, n04, n05, n06, phone) VALUES (?,?,?,?,?,?,?,?)");
            pSt.setString(1, fullname);
            pSt.setLong(2, n01);
            pSt.setLong(3, n02);
            pSt.setLong(4, n03);
            pSt.setLong(5, n04);
            pSt.setLong(6, n05);
            pSt.setLong(7, n06);
            pSt.setString(8, phone);

            pSt.executeUpdate();
            System.out.println("ticket has been inserted - insertTicket()");

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void insertRandomTicket(){
        System.out.println("generating random ticket - insertRandomTicket()");
        int[] arr = new int[6];

        int counter = 0;
        while(counter <= 5){
            arr[counter] = new Random().nextInt(49)+1;
            counter++;
        }
        String fullname = RandomStringUtils.random(10, true, false);
        String phone = RandomStringUtils.random(7, false, true);


        insertTicket(fullname, phone, arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
        System.out.println("random ticket has been inserted - insertRandomTicket()");
    }

    public static void insertWinningTicket(String fullname, long n01, long n02, long n03, long n04, long n05, long n06, String phone, long found){
        try {
            stricMode();
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO winningtickets(fullname, n01, n02, n03, n04, n05, n06, phone, udate, ufound) VALUES (?,?,?,?,?,?,?,?,now(),?)");
            pSt.setString(1, fullname);
            pSt.setLong(2, n01);
            pSt.setLong(3, n02);
            pSt.setLong(4, n03);
            pSt.setLong(5, n04);
            pSt.setLong(6, n05);
            pSt.setLong(7, n06);
            pSt.setString(8, phone);
            pSt.setLong(9, found);

            pSt.executeUpdate();
            System.out.println("winning ticket has been inserted - insertWinningTicket()");

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertWinningNumber(Set<Integer> ticket){
        try {
            stricMode();
            long[] winningTicket = new long[6];
            int i = 0;
            for(Integer num : ticket){
                winningTicket[i] = num;
                System.out.println(num);
                i++;
            }
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO winningnumbers(n01, n02, n03, n04, n05, n06, udate) VALUES (?,?,?,?,?,?,now())");
            pSt.setLong(1, winningTicket[0]);
            pSt.setLong(2, winningTicket[1]);
            pSt.setLong(3, winningTicket[2]);
            pSt.setLong(4, winningTicket[3]);
            pSt.setLong(5, winningTicket[4]);
            pSt.setLong(6, winningTicket[5]);

            pSt.executeUpdate();
            System.out.println("winning numbers have been inserted - insertWinningNumber()");

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Set<Integer> getWinningNumber(){

        Set<Integer> winningTicket = new HashSet<>();
        try {
            stricMode();
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT n01, n02, n03, n04, n05, n06 from winningnumbers");

            ResultSet rs = pSt.executeQuery();

            System.out.println("getting winning numbers - getWinningNumber()");

            while (rs.next()){
                winningTicket.add((int) rs.getLong("n01"));
                winningTicket.add((int) rs.getLong("n02"));
                winningTicket.add((int) rs.getLong("n03"));
                winningTicket.add((int) rs.getLong("n04"));
                winningTicket.add((int) rs.getLong("n05"));
                winningTicket.add((int) rs.getLong("n06"));
            }

            rs.close();
            pSt.close();
            conn.close();

            for(int i : winningTicket){
                System.out.print(i +" ");
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  winningTicket;
    }


    public static List<String> getWinningTicket(){
        List<String> winningTickets = new LinkedList<>();
        try {
            stricMode();
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT fullname, phone, n01, n02, n03, n04, n05, n06, id from tickets");

            ResultSet rs = pSt.executeQuery();

            System.out.println("getting winning ticket- getWinningTicket()");

            int counter = 0;
            while (rs.next()){
                Set<Integer> ticket2 = new HashSet<>();
                ticket2.add((int) rs.getLong("n01"));
                ticket2.add((int) rs.getLong("n02"));
                ticket2.add((int) rs.getLong("n03"));
                ticket2.add((int) rs.getLong("n04"));
                ticket2.add((int) rs.getLong("n05"));
                ticket2.add((int) rs.getLong("n06"));
                if(compareTwoTickets(ticket2, getWinningNumber()) == 4){
                    insertWinningTicket(rs.getString("fullname"), rs.getLong("n01"), rs.getLong("n02"), rs.getLong("n03"),  rs.getLong("n04"), rs.getLong("n05"), rs.getLong("n06"), rs.getString("phone"), 4);
                    System.out.println("found ticket with 4 pairs");
                    winningTickets.add(rs.getString("fullname").trim());
                    winningTickets.add(rs.getString("phone"));
                    winningTickets.add("4 Pairs");
                    winningTickets.add(String.valueOf(rs.getLong("n01")));                    winningTickets.add(String.valueOf(rs.getLong("n01")));
                    winningTickets.add(String.valueOf(rs.getLong("n02")));
                    winningTickets.add(String.valueOf(rs.getLong("n03")));
                    winningTickets.add(String.valueOf(rs.getLong("n04")));
                    winningTickets.add(String.valueOf(rs.getLong("n05")));
                    winningTickets.add(String.valueOf(rs.getLong("n06")));
                } else if (compareTwoTickets(ticket2, getWinningNumber()) == 5) {
                    insertWinningTicket(rs.getString("fullname"), rs.getLong("n01"), rs.getLong("n02"), rs.getLong("n03"),  rs.getLong("n04"), rs.getLong("n05"), rs.getLong("n06"), rs.getString("phone"), 5);
                    System.out.println("found ticket with 5 pairs");
                    winningTickets.add(rs.getString("fullname").trim());
                    winningTickets.add(rs.getString("phone"));
                    winningTickets.add("5 Pairs");
                    winningTickets.add(String.valueOf(rs.getLong("n01")));                    winningTickets.add(String.valueOf(rs.getLong("n01")));
                    winningTickets.add(String.valueOf(rs.getLong("n02")));
                    winningTickets.add(String.valueOf(rs.getLong("n03")));
                    winningTickets.add(String.valueOf(rs.getLong("n04")));
                    winningTickets.add(String.valueOf(rs.getLong("n05")));
                    winningTickets.add(String.valueOf(rs.getLong("n06")));
                } else if (compareTwoTickets(ticket2, getWinningNumber()) == 6) {
                    insertWinningTicket(rs.getString("fullname"), rs.getLong("n01"), rs.getLong("n02"), rs.getLong("n03"),  rs.getLong("n04"), rs.getLong("n05"), rs.getLong("n06"), rs.getString("phone"), 6);
                    System.out.println("BOOM BOOM BOOM");
                    System.out.println("found ticket with 6 pairs");
                    winningTickets.add(rs.getString("fullname").trim());
                    winningTickets.add(rs.getString("phone"));
                    winningTickets.add("6 Pairs");
                    winningTickets.add(String.valueOf(rs.getLong("n01")));                    winningTickets.add(String.valueOf(rs.getLong("n01")));
                    winningTickets.add(String.valueOf(rs.getLong("n02")));
                    winningTickets.add(String.valueOf(rs.getLong("n03")));
                    winningTickets.add(String.valueOf(rs.getLong("n04")));
                    winningTickets.add(String.valueOf(rs.getLong("n05")));
                    winningTickets.add(String.valueOf(rs.getLong("n06")));
                } else {
                    System.out.println("there was no winning ticket");
                }
                counter++;
            }

            rs.close();
            pSt.close();
            conn.close();

            counter = 1;
            for(String s : winningTickets){
                System.out.print(s + " ");
                if(counter <= 3) System.out.print("- ");
                if (counter == 9) counter = 1;
                counter++;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  winningTickets;

    }


    public static void dropWinningNumbers(){
        try {
            stricMode();
            Class.forName("org.postgresql.Driver");


            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DROP TABLE winningnumbers;\n" +
                    "\n" +
                    "CREATE TABLE winningnumbers\n" +
                    "(\n" +
                    "  id bigserial NOT NULL,\n" +
                    "  n01 bigint,\n" +
                    "  n02 bigint,\n" +
                    "  n03 bigint,\n" +
                    "  n04 bigint,\n" +
                    "  n05 bigint,\n" +
                    "  n06 bigint,\n" +
                    "  udate date,\n" +
                    "  CONSTRAINT winningnumbers_pkey PRIMARY KEY (id)\n" +
                    ")\n" +
                    "WITH (\n" +
                    "  OIDS=FALSE\n" +
                    ");\n" +
                    "ALTER TABLE winningnumbers\n" +
                    "  OWNER TO fasttrackit_dev;\n");

            pSt.executeUpdate();
            System.out.println("winning numbers have been dropped - dropWinningNumbers()");

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTicketTable(){
        try {
            stricMode();
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("DROP TABLE tickets;\n" +
                    "\n" +
                    "CREATE TABLE tickets\n" +
                    "(\n" +
                    "  id bigserial NOT NULL,\n" +
                    "  fullname character(50),\n" +
                    "  n01 bigint,\n" +
                    "  n02 bigint,\n" +
                    "  n03 bigint,\n" +
                    "  n04 bigint,\n" +
                    "  n05 bigint,\n" +
                    "  n06 bigint,\n" +
                    "  phone character(7),\n" +
                    "  CONSTRAINT tickets_pkey PRIMARY KEY (id)\n" +
                    ")\n" +
                    "WITH (\n" +
                    "  OIDS=FALSE\n" +
                    ");\n" +
                    "ALTER TABLE tickets\n" +
                    "  OWNER TO fasttrackit_dev;\n");

            pSt.executeUpdate();
            System.out.println("tickets table has been dropped - dropTicketTable()");

            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Set<Integer> generateTicket() {

        Set<Integer> ticket = new HashSet<>();

        System.out.println("generating ticket - generateTicket");

        while(ticket.size() != 6){
            ticket.add(new Random().nextInt(49)+1);
        }
        return ticket;
    }

    public static List<String> generateTicket(String fullname, String phone){
        List<String> ticket = new LinkedList<>();

        ticket.add(fullname);
        ticket.add(phone);

        while(ticket.size() != 6){
            ticket.add(String.valueOf(new Random().nextInt(49)+1));
        }

        return ticket;
    }


    public static int compareTwoTickets(Set<Integer> ticket1, Set<Integer> ticket2) {
        int found=0;

        System.out.println("comparing tickets - compareTwoTickets");

        for(Integer elem : ticket2){
            if(ticket1.contains(elem)){
                found++;
            }
        }

        return found;
    }

    public static List<String> getAllTickets(){
        List<String> tickets = new LinkedList<>();

        try{
            stricMode();
        Class.forName("org.postgresql.Driver");


        final String URL = "jdbc:postgresql://54.93.65.5:5432/flavius8";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT * FROM tickets");
        ResultSet rs = pSt.executeQuery();

        while(rs.next()){
            tickets.add(rs.getString("fullname").trim());
            tickets.add(rs.getString("phone"));
            tickets.add(String.valueOf(rs.getLong("n01")));
            tickets.add(String.valueOf(rs.getLong("n02")));
            tickets.add(String.valueOf(rs.getLong("n03")));
            tickets.add(String.valueOf(rs.getLong("n04")));
            tickets.add(String.valueOf(rs.getLong("n05")));
            tickets.add(String.valueOf(rs.getLong("n06")));
        }

        System.out.println("getting all tickets - getAllTickets()");

        rs.close();
        pSt.close();
        conn.close();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return tickets;
    }

//    public static void displayMenu(){
//        System.out.println("");
//        System.out.println("===============================");
//        System.out.println("1. Insert ticket");
//        System.out.println("2. Get winning ticket");
//        System.out.println("3. Get winning numbers");
//        System.out.println("4. Drop tickets table");
//        System.out.println("5. Drop and insert winning numbers");
//    }
//    public static void menu() {
//        final int EXIT = 6;
//        Scanner input = new Scanner(System.in);
//        int option;
//
//        displayMenu();
//        System.out.print("Choose a menu option by typing the appropriate index: ");
//        option = input.nextInt();
//
//        while (option != EXIT) {
//            System.out.println("You chose: " + option);
//
//            switch (option) {
//
//                case 1:
//                    String fullname, phone;
//                    int n01, n02, n03, n04, n05, n06;
//                    System.out.println("Insert full name: ");
//                    String first = input.next();
//                    String last = input.next();
//                    fullname = first + " " + last;
//                    System.out.println("Insert phone number: ");
//                    phone = input.next();
//                    System.out.println("Insert ticket numbers: ");
//                    n01 = Integer.parseInt(input.next());
//                    n02 = Integer.parseInt(input.next());
//                    n03 = Integer.parseInt(input.next());
//                    n04 = Integer.parseInt(input.next());
//                    n05 = Integer.parseInt(input.next());
//                    n06 = Integer.parseInt(input.next());
//                    insertTicket(fullname, phone, n01, n02, n03, n04, n05, n06);
//                    break;
//                case 2:
//                    getWinningTicket();
//                    break;
//                case 3:
//                    getWinningNumber();
//                    break;
//                case 4:
//                    dropTicketTable();
//                    break;
//                case 5:
//                    dropWinningNumbers();
//                    insertWinningNumber(generateTicket());
//                    break;
//                default:
//                    System.out.println(option + " does not exist in the menu. Try typing the corresponding number of the option you want to access.");
//                    break;
//            }
//
//            displayMenu();
//            System.out.print("Choose a menu option by typing the appropriate index: ");
//            option = input.nextInt();
//        }
//    }
}

