
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }

    public int countUniqueIPs() {
        //ArrayList<String> uniqueIPs = new ArrayList<String>();
        /*for(LogEntry le : records) {
        String ipAddr = le.getIpAddress();
        if(!uniqueIPs.contains(ipAddr)) {
        uniqueIPs.add(ipAddr);
        }
        }*/
        HashMap<String, Integer> uniqueIPs = countVisitsPerIP();
        return uniqueIPs.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public void printAllHigherThanNum(int num) {
        for(LogEntry le : records) {
            int code = le.getStatusCode();
            if(code > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records) {
            String date = le.getAccessTime().toString();
            String ipAddr = le.getIpAddress();
            if(date.indexOf(someday) != -1 && 
            !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        int count = 0;
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records) {
            int code = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if((code >= low && code <= high) && 
            !uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry le : records) {
            String ipAddress = le.getIpAddress();
            if(!counts.containsKey(ipAddress)) {
                counts.put(ipAddress, 1);
            } else {
                counts.put(ipAddress, counts.get(ipAddress) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> IPs) {
        int mostVisit = 0;
        for(String key : IPs.keySet()) {
            int count = IPs.get(key);
            if(count > mostVisit) {
                mostVisit = count;
            }
        }
        return mostVisit;
    }

    public ArrayList<String> iPsMostsVisits(HashMap<String, Integer> IPs) {
        int maxValue = mostNumberVisitsByIP(IPs);
        ArrayList<String> ipAddress = new ArrayList<String>();
        for(String key : IPs.keySet()) {
            if(IPs.get(key) == maxValue){
                if(!ipAddress.contains(key)) {
                    ipAddress.add(key);
                }
            }
        }
        return ipAddress;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipMap = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4,10);
            String ipAddress = le.getIpAddress();
            ArrayList<String> ip;
            if(!ipMap.containsKey(date)) {
                ip = new ArrayList<String>();
                ip.add(ipAddress);
                ipMap.put(date, ip);
            } else {
                ip = ipMap.get(date);
                //if(!ip.contains(ipAddress)) { //checks duplicate
                ip.add(ipAddress);
                ipMap.put(date, ip);
                //}
            }
        }
        return ipMap;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ip) {
        String day = "";
        int size = 0;
        for(String key : ip.keySet()) {
            if(ip.get(key).size() > size) {
                size = ip.get(key).size();
                day = key;
            }
        }
        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ip, String day) {
        ArrayList<String> ipWithMostAccess;
        HashMap<String, Integer> ips = new HashMap<String, Integer>();
        for(String key : ip.keySet()) {
            if(key.equals(day)) {
                for(int i = 0; i < ip.get(key).size(); i++) {
                    String ipAd = ip.get(key).get(i);
                    if(!ips.containsKey(ipAd)) {
                        ips.put(ipAd, 1);
                    } else {
                        ips.put(ipAd, ips.get(ipAd) + 1);
                    }
                }
            }
        }
        ipWithMostAccess = iPsMostsVisits(ips);
        return ipWithMostAccess;
    }
}
