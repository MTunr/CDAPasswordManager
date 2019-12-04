/**
 * Debugging class for CDA
 */

import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.lang.Math;
import java.lang.*;


public class CDADebugger {
    public static void main(String[] args) {
        CDA e = new CDA();
        System.out.println(e.encrypt("a going".repeat(1000),"aa"));

    }
}
