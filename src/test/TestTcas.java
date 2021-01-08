package test;

import tcas.TCAS;
import externalObjects.Plane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class TestTcas {

    @Test
    public void noPlanesDetected() throws FileNotFoundException {
        File tmpFile = new File("tmpFile");
        PrintStream tmpOut = new PrintStream(tmpFile);
        java.lang.System.setOut(tmpOut);

        Plane localPlane = new Plane(0, 0); // notre avion
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); // avions autours de notre avion
        TCAS tcas = new TCAS(environmentPlanes, localPlane);
        tcas.run();
        Boolean testPass = true;

        try {
            Scanner scanner = new Scanner(tmpFile);

            // now read the file line by line...
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Alerte TA Cercle plein orange") || line.contains("Alerte TA Traffic; Traffic")) {
                    testPass = false;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // handle this
        }
        tmpOut.close();
        tmpFile.delete();
        assert (testPass) : "Un avion qui n'existe pas a été détecté";
    }

    @Test
    public void planeDetected() throws FileNotFoundException {
        File tmpFile = new File("tmpFile");
        PrintStream tmpOut = new PrintStream(tmpFile);
        java.lang.System.setOut(tmpOut);

        Plane localPlane = new Plane(0, 0); // notre avion
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); // avions autours de notre avion
        environmentPlanes.add(new Plane(2.3, 750)); 
        TCAS tcas = new TCAS(environmentPlanes, localPlane);
        tcas.run();
        Boolean testPass = false;

        try {
            Scanner scanner = new Scanner(tmpFile);

            // now read the file line by line...
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Alerte TA Cercle plein orange") || line.contains("Alerte TA Traffic; Traffic")) {
                    testPass = true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // handle this
        }
        tmpOut.close();
        tmpFile.delete();
        assert (testPass) : "Un avion qui existe n'a pas été détecté";
    }

    @Test
    public void orangeCircle() throws FileNotFoundException {
        File tmpFile = new File("tmpFile");
        PrintStream tmpOut = new PrintStream(tmpFile);
        java.lang.System.setOut(tmpOut);

        Plane localPlane = new Plane(0, 0); // notre avion
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); // avions autours de notre avion
        Plane plane1 = new Plane(2.3, 750);
        environmentPlanes.add(plane1);
        TCAS tcas = new TCAS(environmentPlanes, localPlane);
        tcas.run();
        Boolean testPass = false;

        try {
            Scanner scanner = new Scanner(tmpFile);

            // now read the file line by line...
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Alerte TA Cercle plein orange")) {
                    testPass = true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // handle this
        }
        tmpOut.close();
        tmpFile.delete();
        assert (testPass) : "L'avion a été détecté mais pas affiché en cercle orange";
    }

    @Test
    public void vocalAlert() throws FileNotFoundException {
        File tmpFile = new File("tmpFile");
        PrintStream tmpOut = new PrintStream(tmpFile);
        java.lang.System.setOut(tmpOut);

        Plane localPlane = new Plane(0, 0); // notre avion
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); // avions autours de notre avion
        Plane plane1 = new Plane(2.3, 750);
        environmentPlanes.add(plane1);
        TCAS tcas = new TCAS(environmentPlanes, localPlane);
        tcas.run();
        Boolean testPass = false;

        try {
            Scanner scanner = new Scanner(tmpFile);

            // now read the file line by line...
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Alerte TA Traffic; Traffic")) {
                    testPass = true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // handle this
        }
        tmpOut.close();
        tmpFile.delete();
        assert (testPass) : "L'avion a été détecté mais pas alerté vocalement";
    }

    @Test
    public void functionalHLR() {
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); // avions autours de notre avion

        Plane plane1 = new Plane(2, 600);
        Plane plane2 = new Plane(2.3, 750);
        Plane plane3 = new Plane(4, 1000);
        Plane plane4 = new Plane(7, 1400);

        environmentPlanes.add(plane1);
        environmentPlanes.add(plane2);
        environmentPlanes.add(plane3);
        environmentPlanes.add(plane4);
    }

}