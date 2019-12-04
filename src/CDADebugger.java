/**
 * Debugging class for CDA
 */
public class CDADebugger {
    public static void main(String[] args) {
        CDA e = new CDA();
        System.out.println(e.keyExpander("hello"));
        System.out.println(e.keyExpander("hellp"));
        System.out.println(e.keyExpander("gello"));
        System.out.println(e.keyExpander("o"));
        System.out.println(e.keyExpander("fairly long password i gues"));
        System.out.println(e.keyExpander("fairly long password i guess"));

        //803999654436288 - hellp
        //803851191445120 - hello
        //866474805345920 - gello
        //
        System.out.println("helldddd".hashCode());
    }
}
