package net.gentledot.maskit;

import net.gentledot.maskit.models.DataTypes;

public class Main {
    public static void main(String[] args) {
        DataMasking dataMasking = new DataMasking();
        String maskedEmail = dataMasking.getModule(DataTypes.EMAIL).mask("example@example.com");
        System.out.println("Masked Email: " + maskedEmail); // Masked Email: e***@example.com
    }
}