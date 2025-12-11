
package utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class DireccionUtils {
    
    public static final String PUERTO = "54000";
    
    public static String obtenerIPsReales() {
        StringBuilder ipsReales = new StringBuilder();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
                    continue;
                }

                Enumeration<InetAddress> direcciones = ni.getInetAddresses();
                while (direcciones.hasMoreElements()) {
                    InetAddress addr = direcciones.nextElement();

                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        ipsReales.append("IP real encontrada: ").append(addr.getHostAddress()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ipsReales.length() == 0) {
            return "No se encontró ninguna IP real.";
        }

        return ipsReales.toString();
    }
    
}
