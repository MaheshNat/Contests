import Constants;

import java.util.*;
import java.io.*;

public class Day12 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day12")));
        List<Moon> moons = new ArrayList<Moon>();
        List<List<Moon>> previousMoons = new ArrayList<List<Moon>>();
        while (in.hasNextLine()) {
            int[] parts = Arrays.stream(in.nextLine().replaceAll("[^0-9-,]", "").split(","))
                    .mapToInt(x -> Integer.parseInt(x)).toArray();
            moons.add(new Moon(parts[0], parts[1], parts[2]));
        }

        moons.forEach(x -> System.out.println(x));
        System.out.println();

        int i = 0;
        while (true) {
            for (Moon moon : moons) {
                for (Moon other : moons) {
                    if (moon.equals(other))
                        continue;
                    moon.xVel += moon.x < other.x ? 1 : (moon.x > other.x ? -1 : 0);
                    moon.yVel += moon.y < other.y ? 1 : (moon.y > other.y ? -1 : 0);
                    moon.zVel += moon.z < other.z ? 1 : (moon.z > other.z ? -1 : 0);
                }
            }
            for (Moon moon : moons)
                moon.updatePosition();
            if (repeated(moons, previousMoons))
                break;
            previousMoons.add(new ArrayList<Moon>() {{moons.forEach(x -> put(x));}});
            i++;

            System.out.println(moons);
            System.out.println(previousMoons);
        }

        System.out.println(i);

        int totalEnergy = 0;
        for (Moon moon : moons)
            totalEnergy += moon.getEnergy();

        System.out.println(totalEnergy);

    }

    private static boolean repeated(List<Moon> moons, List<List<Moon>> previousMoons) {
        for (List<Moon> previousMoon : previousMoons)
            if (moons.equals(previousMoon))
                return true;
        return false;
    }

    private static class Moon {
        int x, y, z;
        int xVel, yVel, zVel;

        Moon(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            Moon m = (Moon) o;
            return x == m.x && y == m.y && z == m.z && xVel == m.xVel && yVel == m.yVel && zVel == m.zVel;
        }

        @Override
        public String toString() {
            return String.format("x: %d, y: %d, z: %d, xVel: %d, yVel: %d, zVel: %d", x, y, z, xVel, yVel, zVel);
        }

        void updatePosition() {
            x += xVel;
            y += yVel;
            z += zVel;
        }

        int getEnergy() {
            return (Math.abs(x) + Math.abs(y) + Math.abs(z)) * (Math.abs(xVel) + Math.abs(yVel) + Math.abs(zVel));
        }
    }
}