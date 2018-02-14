



import java.util.*;

public class VilleI {



    public static void main(String[] args) {
        String[] nimet = new String[]{
                "Aino", "Aleksi H.", "Aleksi P.", "Jani", "Johanna J.", "Heidi K.", "Joni",
                "Tom", "Juuso", "Hanna-Leena", "Johanna L.", "Milla", "Vellu", "Heidi N.",
                "Sami", "Outi", "Elina", "Renne", "Olli", "Toni", "Paula", "Leena", "Nikita",
                "Tiina K.", "Tiina E.", "Antti", "Ville", "Waltteri", "Satu"
        };
        List<String> nimilista = new ArrayList<>(Arrays.asList(nimet));
        Collections.shuffle(nimilista);
        String randomnimi = nimilista.get(0);
        System.out.println(randomnimi);



    }
}
//            Collections.shuffle(nimilista);
//
//
//
//            public static Item getRandomChestItem() {
//                try {
//                    return items.get((new Random()).nextInt(items.size()));
//                }
//                catch (Throwable e){
//                    return null;
//                }
//            }
//
//
//
//
//
//            List<String>[] yksilo = arvo(nimilista, )
//
//            List<String>[] ryhmat = jaa(nimilista, 7);
//            tulostaRyhmät(ryhmat);
//        }
//
//    private static List<String>[] jaa(List<String> nimet, final int yksilo) {
//
//        private static void tulostaRyhmät(List<String>[] ryhmat) {
//            String jasenet=null;
//            for (int i = 1; i <= ryhmat.length; i++) {
//                for(String nimi: ryhmat[i-1]) {
//                    jasenet = String.join(", ", ryhmat[i-1]);
//                }
//                System.out.println(String.format("%d: %s", i, jasenet));
//            }
//        }
//
//        private static List<String>[] jaa(List<String> nimet, final int ryhmia) {
//            List<String> kopio = new ArrayList<>(nimet);
//            int hloitaPerRyhma =  kopio.size() / ryhmia;
//            int isompiaRyhmia = kopio.size() % ryhmia;
//            List<String>[] ryhmat = new List[ryhmia];
//            for (int i = 0; i < ryhmia; i++) {
//                ryhmat[i] = new ArrayList<>();
//                for (int j = 0; j < hloitaPerRyhma; j++) {
//                    ryhmat[i].add( kopio.remove(0));
//                }
//                if(isompiaRyhmia-- > 0) {
//                    ryhmat[i].add(kopio.remove(0));
//                }
//            }
//            return ryhmat;
//        }
//    }
