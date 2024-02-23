public class Main {
    public static void main(String[] args) {
        PetriNet petriNet = new PetriNet();
        petriNet.addPlace("S1");
        petriNet.addPlace("S2");
        petriNet.addPlace("S3");
        petriNet.addPlace("S4");


        petriNet.createTransition("T1");
        petriNet.addPreSet("T1", "S4", 3);
        petriNet.addPostSet("T1", "S1", 3);
        petriNet.addPostSet("T1", "S2");

        petriNet.createTransition("T2");
        petriNet.addPreSet("T2", "S2");
        petriNet.addPostSet("T2", "S3", 3);

        petriNet.createTransition("T3");
        petriNet.addPreSet("T3", "S1");
        petriNet.addPreSet("T3", "S3");
        petriNet.addPostSet("T3", "S4");

        petriNet.print();
        petriNet.setInitialMarking("S4", 3);

        petriNet.printMarking();
        petriNet.fireTransition("T1");
        petriNet.printMarking();

        petriNet.printTransitionActivation();
    }

}
