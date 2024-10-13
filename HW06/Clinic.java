import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Clinic {
    private File patientFile;
    private int day;

    public Clinic(File file) {
        this.patientFile = file;
        this.day = 1;
    }

    public Clinic(String fileName) {
        this(new File(fileName));
    }

    public String nextDay(File f) throws FileNotFoundException {
        this.day++;

        Scanner fileScan = null;
        Scanner input = new Scanner(System.in);
        Scanner appointmentScan = null;
        String output = "";

        try {
            fileScan = new Scanner(f);
            String line = null;
            while (fileScan.hasNextLine()) {
                line = fileScan.nextLine();
                appointmentScan = new Scanner(line);
                appointmentScan.useDelimiter(",");

                String name = appointmentScan.next();
                String type = appointmentScan.next();
                String stat = appointmentScan.next();
                String time = appointmentScan.next();

                if (!type.equals("Dog") && !type.equals("Cat")) {
                    throw new InvalidPetException();
                }

                System.out.printf("Consultation for %s the %s at %s.\n", name, type, time);

                boolean isHeath = false;
                double health = 0.0;
                while (!isHeath) {
                    try {
                        System.out.printf("What is the health of %s?\n", name);
                        health = input.nextDouble();
                        isHeath = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Sorry, that wasn't an double.");
                        System.out.println("Please try again");
                    } finally {
                        input.nextLine();
                    }
                }

                boolean ispainLevel = false;
                int painLevel = 0;
                while (!ispainLevel) {
                    try {
                        System.out.printf("On a scale of 1 to 10, how much pain is %s in right now?\n", name);
                        painLevel = input.nextInt();
                        ispainLevel = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Sorry, that wasn't an int.");
                        System.out.println("Please try again");
                    } finally {
                        input.nextLine();
                    }
                }

                Pet petPatient = null;
                switch (type) {
                    case "Dog":
                        petPatient = new Dog(name, health, painLevel, Double.parseDouble(stat));
                        break;
                    case "Cat":
                        petPatient = new Cat(name, health, painLevel, Integer.parseInt(stat));
                        break;
                    default:
                        break;
                }

                double initialHealth = petPatient.getHealth();
                int initialPainLevel = petPatient.getPainLevel();
                petPatient.speak();
                String exitTime = addTime(time, petPatient.treat());
                output += String.format("%s,%s,%s,Day %d,%s,%s,%s,%d\n", name, type, stat, day, time, exitTime,
                        String.valueOf(initialHealth),
                        initialPainLevel);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileScan != null) {
                fileScan.close();
            }
        }
        return output.trim();
    }

    public String nextDay(String fileName) throws FileNotFoundException {
        return nextDay(new File(fileName));
    }

    public boolean addToFile(String patientInfo) {
        Scanner fileScan = null;
        Scanner patientInfoScan = null;
        Scanner appointmentScan = null;
        PrintWriter filePrint = null;
        String output = "";

        try {
            patientInfoScan = new Scanner(patientInfo);
            patientInfoScan.useDelimiter(",");
            String name = patientInfoScan.next();
            String type = patientInfoScan.next();
            String stat = patientInfoScan.next();
            String day = patientInfoScan.next();
            String entryTime = patientInfoScan.next();
            String exitTime = patientInfoScan.next();
            String health = patientInfoScan.next();
            String painLevel = patientInfoScan.next();

            boolean newPatient = true;
            fileScan = new Scanner(patientFile);
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                appointmentScan = new Scanner(line);
                appointmentScan.useDelimiter(",");
                if (line.startsWith(name)) {
                    newPatient = false;
                    line += String.format(",%s,%s,%s,%s,%s", day, entryTime, exitTime, health, painLevel);
                }
                output += (line + "\n");
            }

            if (newPatient) {
                output += patientInfo;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileScan != null) {
                fileScan.close();
            }
        }

        try {
            filePrint = new PrintWriter(patientFile);
            filePrint.println(output);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (filePrint != null) {
                filePrint.close();
            }
        }
    }

    private String addTime(String timeIn, int treatmentTime) {
        String[] timeInArray = timeIn.split("");
        int hour = Integer.parseInt(timeInArray[0] + timeInArray[1]);
        int minute = Integer.parseInt(timeInArray[2] + timeInArray[3]);
        int addHour = treatmentTime / 60;
        int addMinute = treatmentTime % 60;
        int addedHour = hour + addHour;
        int addedMinute = minute + addMinute;
        String result = "";
        if (Integer.toString(addedHour).length() < 2) {
            result = "0" + Integer.toString(addedHour) + Integer.toString(addedMinute);
        } else {
            result = Integer.toString(addedHour) + Integer.toString(addedMinute);
        }
        return result;
    }
}
