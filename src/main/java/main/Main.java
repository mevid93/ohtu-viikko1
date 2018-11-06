package main;

import ohtu.ohtuvarasto.Varasto;



public class Main {

    // magic numbers
    private static final int TILAVUUS = 100;
    private static final int ISO_OTTO = 1000;
    private static final double KESKISUURI = 50.7;
    private static final int ISO_NEGATIIVINEN = -666;
    private static final double PIENI_NEGATIIVINEN = -32.9;
    private static final double PIENI_OTTO = 3.14;
    private static final double ALKUKAMAT = 20.2;

    // varastot
    private static Varasto mehua = new Varasto(TILAVUUS);
    private static Varasto olutta = new Varasto(TILAVUUS, ALKUKAMAT);

    public static void main(String[] args) {
        tilanneLuonninJalkeen();
        mehusetterit();
        virhetilanteita();
        olutLisays();
        mehuLisays();
        olutOtto();
        mehuOtto();
    }

    private static void tilanneLuonninJalkeen() {
        System.out.println("Luonnin jälkeen:");
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Olutvarasto: " + olutta);

        System.out.println("Olutgetterit:");
        System.out.println("getSaldo()     = " + olutta.getSaldo());
        System.out.println("getTilavuus    = " + olutta.getTilavuus());
        System.out.println("paljonkoMahtuu = " + olutta.paljonkoMahtuu());
    }

    private static void mehusetterit() {
        System.out.println("Mehusetterit:");
        System.out.println("Lisätään 50.7");
        mehua.lisaaVarastoon(KESKISUURI);
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Otetaan 3.14");
        mehua.otaVarastosta(PIENI_OTTO);
        System.out.println("Mehuvarasto: " + mehua);
    }

    private static void virhetilanteita() {
        System.out.println("Virhetilanteita:");
        System.out.println("new Varasto(-100.0);");
        Varasto huono = new Varasto(-TILAVUUS);
        System.out.println(huono);

        System.out.println("new Varasto(100.0, -50.7)");
        huono = new Varasto(TILAVUUS, -KESKISUURI);
        System.out.println(huono);
    }

    private static void olutLisays() {
        System.out.println("Olutvarasto: " + olutta);
        System.out.println("olutta.lisaaVarastoon(1000.0)");
        olutta.lisaaVarastoon(ISO_OTTO);
        System.out.println("Olutvarasto: " + olutta);
    }

    private static void mehuLisays() {
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("mehua.lisaaVarastoon(-666.0)");
        mehua.lisaaVarastoon(ISO_NEGATIIVINEN);
        System.out.println("Mehuvarasto: " + mehua);
    }

    private static void olutOtto() {
        System.out.println("Olutvarasto: " + olutta);
        System.out.println("olutta.otaVarastosta(1000.0)");
        double saatiin = olutta.otaVarastosta(ISO_OTTO);
        System.out.println("saatiin " + saatiin);
        System.out.println("Olutvarasto: " + olutta);
    }

    private static void mehuOtto() {
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("mehua.otaVarastosta(-32.9)");
        double saatiin = mehua.otaVarastosta(PIENI_NEGATIIVINEN);
        System.out.println("saatiin " + saatiin);
        System.out.println("Mehuvarasto: " + mehua);
    }
}
