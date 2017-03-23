
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        ArrayList<String> IPs = la.uniqueIPVisitsOnDay("Sep 27");
        for(String ip : IPs) {
            System.out.println(ip);
        }
        System.out.println("Has " + IPs.size() + " IPs");
    }

    public void testUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        int count = la.countUniqueIPsInRange(200, 299); //4
        //int count = la.countUniqueIPsInRange(300, 399); //2
        System.out.println(count);
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        for(String key : counts.keySet()) {
            System.out.println(key + "\t- " + counts.get(key));
        }
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }

    public void testIPsMostsVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> IPs = la.iPsMostsVisits(counts);
        for(int i = 0; i < IPs.size(); i++) {
            System.out.println(IPs.get(i));
        }
    }

    public void testIpsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog3-short_log");
        HashMap<String, ArrayList<String>> ip = la.iPsForDays();
        for(String key : ip.keySet()) {
            System.out.println(key + " " + ip.get(key).size() +" IPs");
        }
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    }
    
    public void testIpsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog2_log");
        HashMap<String, ArrayList<String>> ip = la.iPsForDays();
        ArrayList<String> abc = la.iPsWithMostVisitsOnDay(ip, "Sep 29");
        for(int i = 0; i < abc.size(); i++) {
            System.out.println(abc.get(i));
        }
    }
}
