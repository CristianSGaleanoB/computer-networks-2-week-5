import java.net.InetAddress;

public class AutomatizationPing {
    public static void main(String[] args) {
        String[] DNS ={
                "hukumusume.com", "bbc.co.uk", "amazon.fr", "lanacion.com.ar",
                "elmundo.es", "baidu.com", "sis.gov.eg", "google.de", "elespectador.com"
        };

        for (int i = 0; i < 10; i++) {
            System.out.println(i  + 1);
            for (String dns : DNS){
                try {
                    InetAddress ip = InetAddress.getByName(dns);
                    long start = System.currentTimeMillis();
                    boolean reachable = ip.isReachable(5000); // 5 seconds for an answer if else throw false.
                    long end = System.currentTimeMillis();

                    if (reachable){
                        System.out.println(dns + " is reachable in: " + (end - start) + "ms");
                    }else {
                        System.out.println(dns + " is unreachable");
                    }
                }catch (Exception e){
                    System.out.println(dns + " caused an error: " + e.getMessage());
                }
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}