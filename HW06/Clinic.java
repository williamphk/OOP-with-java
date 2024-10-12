import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
        Scanner fileScan = null;
        Scanner appointmentScan = null;
        String message = "";
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
                double droolRate = 0.0;
                int miceCaught = 0;

                if (type.equals("Dog")) {
                    droolRate = appointmentScan.nextDouble();
                } else if (type.equals("Cat")) {
                    miceCaught = appointmentScan.nextInt();
                } else {
                    throw new InvalidPetException();
                }

                String time = appointmentScan.next();

                Scanner input = new Scanner(System.in);
                boolean isHeath = false;
                double health = 0.0;
                while (!isHeath) {
                    try {
                        System.out.println("Consultation for " + name + " the " + type + " at " + time + ".\n" +
                                "What is the health of " + name + "?");
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
                        System.out.println("On a scale of 1 to 10, how much pain is " + name + " in right now?");
                        painLevel = input.nextInt();
                        ispainLevel = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Sorry, that wasn't an int.");
                        System.out.println("Please try again");
                    } finally {
                        input.nextLine();
                    }
                }

                // if (input != null) {
                // input.close();
                // }

                if (type.equals("Dog")) {
                    Dog dog = new Dog(name, health, painLevel, droolRate);
                    double initialHealth = dog.getHealth();
                    int initialPainLevel = dog.getPainLevel();
                    dog.speak();
                    String exitTime = addTime(time, dog.treat());
                    message = name + "," + type + "," + droolRate + "," + day + "," + time + "," + exitTime + ","
                            + initialHealth + ","
                            + initialPainLevel + "\n";
                    output += message;
                } else if (type.equals("Cat")) {
                    Cat cat = new Cat(name, health, painLevel, miceCaught);
                    double initialHealth = cat.getHealth();
                    int initialPainLevel = cat.getPainLevel();
                    cat.speak();
                    String exitTime = addTime(time, cat.treat());
                    message = name + "," + type + "," + miceCaught + "," + day + "," + time + "," + exitTime + ","
                            + initialHealth + ","
                            + initialPainLevel + "\n";
                    output += message;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileScan != null) {
                fileScan.close();
            }
        }
        return output;
    }

    public String nextDay(String fileName) throws FileNotFoundException {
        return nextDay(new File(fileName));
    }

    public boolean addToFile(String patientInfo) {
        System.out.println("triggered");
        System.out.println(patientInfo);
        PrintWriter filePrint = null;
        boolean result = false;
        Scanner appointmentScan = null;

        try {
            filePrint = new PrintWriter(new FileOutputStream(patientFile, true));
            appointmentScan = new Scanner(patientInfo);
            appointmentScan.useDelimiter(",");
            String name = appointmentScan.next();
            String type = appointmentScan.next();
            double droolRate = 0.0;
            int miceCaught = 0;

            if (type.equals("Dog")) {
                droolRate = appointmentScan.nextDouble();
            } else if (type.equals("Cat")) {
                miceCaught = appointmentScan.nextInt();
            } else {
                throw new InvalidPetException();
            }

            double health = appointmentScan.nextDouble();
            int painLevel = appointmentScan.nextInt();
            if (type.equals("Dog")) {
                Dog currentDog = new Dog(name, health, painLevel, droolRate);
                Scanner fileScan = null;
                try {
                    fileScan = new Scanner(patientFile);
                    String line = null;
                    if (fileScan.hasNextLine()) {
                        while (fileScan.hasNextLine()) {
                            line = fileScan.nextLine();
                            appointmentScan = new Scanner(line);
                            appointmentScan.useDelimiter(",");
                            name = appointmentScan.next();
                            type = appointmentScan.next();

                            if (type.equals("Dog")) {
                                droolRate = appointmentScan.nextDouble();
                            } else if (type.equals("Cat")) {
                                continue;
                            } else {
                                throw new InvalidPetException();
                            }

                            int currentday = appointmentScan.nextInt();
                            String entryTime = appointmentScan.next();
                            String exitTime = appointmentScan.next();

                            health = appointmentScan.nextDouble();
                            painLevel = appointmentScan.nextInt();
                            Dog dog = new Dog(name, health, painLevel, droolRate);
                            System.out.println("Dog");

                            if (currentDog.equals(dog)) {
                                filePrint.println("Day" + currentday + "," + entryTime + "," + exitTime + ","
                                        + health + ","
                                        + painLevel);
                            } else {
                                filePrint.println(patientInfo);
                            }
                        }
                    } else {
                        filePrint.println(patientInfo);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (fileScan != null) {
                        fileScan.close();
                    }
                }

            } else if (type.equals("Cat")) {
                Cat currentCat = new Cat(name, health, painLevel, miceCaught);
                Scanner fileScan = null;
                try {
                    fileScan = new Scanner(patientFile);
                    String line = null;
                    if (fileScan.hasNextLine()) {
                        while (fileScan.hasNextLine()) {
                            line = fileScan.nextLine();
                            appointmentScan = new Scanner(line);
                            appointmentScan.useDelimiter(",");
                            name = appointmentScan.next();
                            type = appointmentScan.next();

                            if (type.equals("Dog")) {
                                continue;
                            } else if (type.equals("Cat")) {
                                miceCaught = appointmentScan.nextInt();
                            } else {
                                throw new InvalidPetException();
                            }

                            int currentday = appointmentScan.nextInt();
                            String entryTime = appointmentScan.next();
                            String exitTime = appointmentScan.next();

                            health = appointmentScan.nextDouble();
                            painLevel = appointmentScan.nextInt();
                            Cat cat = new Cat(name, health, painLevel, miceCaught);
                            if (currentCat.equals(cat)) {
                                filePrint.println(
                                        "Day" + currentday + "," + entryTime + "," + exitTime + "," + health + ","
                                                + painLevel);
                            } else {
                                filePrint.println(patientInfo);
                            }
                        }
                    } else {
                        filePrint.println(patientInfo);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (fileScan != null) {
                        fileScan.close();
                    }
                }
            }
            // filePrint.println(patientInfo);
            result = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            result = false;
        } finally {
            if (filePrint != null) {
                filePrint.close();
            }
        }
        return result;
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
