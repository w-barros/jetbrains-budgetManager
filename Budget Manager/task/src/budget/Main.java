package budget;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    public static void menu(double balance, ArrayList<Product> productList) {
        System.out.println();
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
        Scanner menuScanner = new Scanner(System.in);
        String input = menuScanner.nextLine();
        switch (input) {
            case "1":
                System.out.println();
                addIncome(balance, productList);
            case "2":
                System.out.println();
                addPurchase(balance, productList);
            case "3":
                System.out.println();
                showList(balance, productList);
            case "4":
                System.out.println();
                showBalance(balance, productList);
            case "5":
                System.out.println();
                save(balance, productList);
            case "6":
                System.out.println();
                load(balance, productList);
            case "7":
                System.out.println();
                sort(balance, productList);
            case "0":
                System.out.println();
                System.out.println("Bye!");
                System.exit(0);
                return;
        }
    }
    public static void sort(double balance, ArrayList<Product> productList) {
        System.out.println();
        Scanner scannerSort = new Scanner(System.in);
        System.out.println("How do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
        String input = scannerSort.nextLine();
        switch (input) {
            case "1":
                if (productList.isEmpty()) {
                    System.out.println();
                    System.out.println("The purchase list is empty!");
                    sort(balance,productList);
                } else {
                    Collections.sort(productList, new Comparator<Product>(){

                        public int compare(Product o1, Product o2)
                        {
                            return o2.getValue().compareTo(o1.getValue());
                        }
                    });
                    System.out.println();
                    System.out.println("All:");
                    double sum = 0;
                    for (Product product : productList) {
                        System.out.println(product.getName() + " $" + String.format("%.2f", product.getValue()));
                        sum += product.getValue();
                    }
                    System.out.println("Total sum: $" + String.format("%.2f", sum));
                    sort(balance,productList);
                }
            case "2":
                double Food = 0;
                double Entertainment = 0;
                double Clothes = 0;
                double Other = 0;
                double totalSum = 0;
                for (Product product : productList) {
                    switch (product.getType()) {
                        case "Food":
                            Food += product.getValue();
                            totalSum += product.getValue();
                            continue;
                        case "Entertainment":
                            Entertainment += product.getValue();
                            totalSum += product.getValue();
                            continue;
                        case "Clothes":
                            Clothes += product.getValue();
                            totalSum += product.getValue();
                            continue;
                        case "Other":
                            Other += product.getValue();
                            totalSum += product.getValue();
                            continue;
                    }
                }
                System.out.println();
                System.out.println("Types:\n" +
                        "Food - $"+String.format("%.2f", Food)+"\n" +
                        "Entertainment - $"+String.format("%.2f", Entertainment)+"\n" +
                        "Clothes - $"+String.format("%.2f", Clothes)+"\n" +
                        "Other - $"+String.format("%.2f", Other)+"\n" +
                        "Total sum: $"+String.format("%.2f", totalSum));
                sort(balance,productList);
            case "3":
                System.out.println();
                System.out.println("Choose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other");
                String input3 = scannerSort.nextLine();
                String typeSelected = "";
                switch (input3) {
                    case "1" : typeSelected = "Food";
                    break;
                    case "2" : typeSelected = "Clothes";
                    break;
                    case "3" : typeSelected = "Entertainment";
                    break;
                    case "4" : typeSelected = "Other";
                    break;
                }
                        sortByType(balance, typeSelected, productList);
            case "4":
                menu(balance,productList);
        }
    }

    public static void sortByType(Double balance, String type, ArrayList<Product> productList){
        System.out.println();
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : productList) {
            if (product.getType().equals(type)){
                results.add(product);
            }
        }
        if (results.isEmpty()) {
            System.out.println();
            System.out.println("The purchase list is empty!");
            sort(balance,productList);;
        }
        else {
            Collections.sort(results, new Comparator<Product>(){

                public int compare(Product o1, Product o2)
                {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            System.out.println(type+":");
            double sum = 0;
            for (Product product : results) {
                System.out.println(product.getName() + " $" + String.format("%.2f", product.getValue()));
                sum += product.getValue();
            }
            System.out.println("Total sum: $" + String.format("%.2f", sum));
            sort(balance,productList);
        }

    }


    public static void addIncome(double balance, ArrayList<Product> productList) {
        System.out.println("Enter income:");
        Scanner scannerAddIncome = new Scanner(System.in);
        double valueToAdd = scannerAddIncome.nextDouble();
        balance += valueToAdd;
        System.out.println("Income was added!");
        menu(balance, productList);
    }

    public static void addPurchase(double balance, ArrayList<Product> productList) {
        System.out.println();
        Scanner scannerAddIncome = new Scanner(System.in);
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
        String typeInput = scannerAddIncome.nextLine();
        String typeFinal = "";
        switch (typeInput) {
            case "1":
                typeFinal = "Food";
                break;
            case "2":
                typeFinal = "Clothes";
                break;
            case "3":
                typeFinal = "Entertainment";
                break;
            case "4":
                typeFinal = "Other";
                break;
            case "5":
                menu(balance, productList);
                break;
        }
        System.out.println();
        System.out.println("Enter purchase name:");
        String name = scannerAddIncome.nextLine();
        System.out.println("Enter its price:");
        double value = scannerAddIncome.nextDouble();
        System.out.println("Purchase was added!");

        balance -= value;
        if (balance < 0) {
            balance = 0;
        }
        Product product = new Product(name, round(value,2), typeFinal, balance);
        productList.add(product);
        addPurchase(balance, productList);
    }

    public static void showList(double balance, ArrayList<Product> productList) {
        System.out.println();
        Scanner scannerShowList = new Scanner(System.in);
        System.out.println("Choose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
        String typeInput = scannerShowList.nextLine();
        String typeFinal = "";
        switch (typeInput) {
            case "1":
                typeFinal = "Food";
                break;
            case "2":
                typeFinal = "Clothes";
                break;
            case "3":
                typeFinal = "Entertainment";
                break;
            case "4":
                typeFinal = "Other";
                break;
            case "5":
                typeFinal = "All";
                break;
            case "6":
                menu(balance, productList);
                break;
        }
        System.out.println();
        if (!typeFinal.equals("All")) {
            ArrayList<Product> results = new ArrayList<>();
            double sum = 0;
            for (Product product : productList) {
                if (product.getType().equals(typeFinal)) {
                    sum += product.getValue();;
                    results.add(product);
                }
            }
            if (results.isEmpty()) {
                System.out.println(typeFinal + ":");
                System.out.println("The purchase list is empty!");
            } else {
                System.out.println(typeFinal + ":");
                for (Product product : results) {
                    System.out.println(product.getName() + " $" + String.format("%.2f", product.getValue()));
                }
                System.out.println("Total sum: $" + sum);
            }
        } else {
            if (productList.isEmpty()) {
                System.out.println("All:");
                System.out.println("The purchase list is empty!");
            } else {
                System.out.println("All:");
                double allSum = 0;
                for (Product product : productList) {
                    System.out.println(product.getName() + " $" + String.format("%.2f", product.getValue()));
                    allSum += product.getValue();
                }
                System.out.println("Total sum: $" + allSum);
            }
        }
        showList(balance, productList);
    }

    public static void showBalance(double balance, ArrayList<Product> productList) {
        System.out.println("Balance: $" + balance);
        menu(balance, productList);
    }

    public static void save(double balance, ArrayList<Product> productList) {
        try {
            FileOutputStream fos = new FileOutputStream("purchases.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(productList); // write MenuArray to ObjectOutputStream
            oos.close();
            System.out.println();
            System.out.println("Purchases were saved!");
            menu(balance, productList);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void load(double balance, ArrayList<Product> productList) {
        try {
            FileInputStream fis = new FileInputStream("purchases.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Product> newProductList;
            newProductList = (ArrayList<Product>) ois.readObject();
            Product product = newProductList.get(0);
        //    double newBalance = product.getBalance();
            double totalCost = 0;
            double loadedBalance = 0;
            for (Product productFromFile : newProductList) {
                totalCost += productFromFile.getValue();

            }
            loadedBalance = product.getBalance();
            double newBalance = 1000 - totalCost;
            if (newBalance <0) {
                newBalance = 0;
            }

            System.out.println();
            System.out.println("Purchases were loaded!");
            menu(newBalance, newProductList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

        public static void main(String[] args) {
        double balance = 0;
        ArrayList<Product> productList = new ArrayList<>();
        menu(balance, productList);
    }
}
