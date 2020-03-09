import Constants;

import java.util.*;
import java.io.*;

public class Day8 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day8")));
        List<int[][]> image = new ArrayList<int[][]>();
        String input = in.nextLine();

        int rows = 6, cols = 25;
        int row = 0, col = 0, layer = 0;
        for (int i = 0; i < input.length(); i++) {
            if (image.size() <= layer)
                image.add(new int[rows][cols]);
            image.get(layer)[row][col] = Character.getNumericValue(input.charAt(i));
            col++;
            if (col == cols) {
                col = 0;
                row++;
            }
            if (row == rows) {
                row = 0;
                layer++;
            }
        }

        int fewestZeroDigits = Integer.MAX_VALUE;
        int fewestZeroDigitsLayer = -1;
        for (int i = 0; i < image.size(); i++) {
            int zeros = 0;
            for (int j = 0; j < image.get(i).length; j++)
                for (int k = 0; k < image.get(i)[j].length; k++)
                    if (image.get(i)[j][k] == 0)
                        zeros++;
            if (zeros < fewestZeroDigits) {
                fewestZeroDigits = zeros;
                fewestZeroDigitsLayer = i;
            }
        }

        int ones = 0, twos = 0;
        int[][] fewestLayer = image.get(fewestZeroDigitsLayer);
        for (int i = 0; i < fewestLayer.length; i++) {
            for (int j = 0; j < fewestLayer[i].length; j++) {
                if (fewestLayer[i][j] == 1)
                    ones++;
                else if (fewestLayer[i][j] == 2)
                    twos++;
            }
        }

        System.out.println(ones * twos);

        int[][] finalImage = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int finalColor = -1;
                for (int[][] layerImage : image) {
                    if (layerImage[i][j] == 2)
                        continue;
                    finalColor = layerImage[i][j];
                    break;
                }
                finalImage[i][j] = finalColor;
            }
        }

        for (int i = 0; i < finalImage.length; i++) {
            for (int j = 0; j < finalImage[i].length; j++) {
                System.out.print(finalImage[i][j] == 1 ? "#" : " ");
            }
            System.out.println();
        }
    }
}