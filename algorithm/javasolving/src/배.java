import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//배 1092
public class 배 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        List<Integer> crains = new ArrayList<>();
        String[] crainInputs = br.readLine().split(" ");
        for (String crain : crainInputs) {
            crains.add(Integer.valueOf(crain));
        }


        int m = Integer.parseInt(br.readLine());
        List<Integer> cargos = new ArrayList<>();
        String[] cargoInputs = br.readLine().trim().split(" ");
        for (String cargo : cargoInputs) {
            cargos.add(Integer.valueOf(cargo));
        }
        crains.sort(Collections.reverseOrder());
        cargos.sort(Collections.reverseOrder());

        if (cargos.get(0) > crains.get(0)) {
            System.out.println(-1);
            return;
        }


        int count = 0;
        while (!cargos.isEmpty()) {
            count++;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < cargos.size(); j++) {
                    if (crains.get(i) >= cargos.get(j)) {
                        cargos.remove(j);
                        break;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
