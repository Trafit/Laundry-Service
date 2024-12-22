package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import java.io.File;


public class LaundryTest {

    @Test
    public void testCalculateTotalPrice() {
        double hargaPakaian = Laundry.calculateTotalPrice("Clothes", 5);
        double hargaBedding = Laundry.calculateTotalPrice("Bedding", 2);
        double hargaLainnya = Laundry.calculateTotalPrice("Others", 3);

        assertEquals(25.0, hargaPakaian, 0.01);
        assertEquals(16.0, hargaBedding, 0.01);
        assertEquals(18.0, hargaLainnya, 0.01);
    }

    @Test
    public void testCalculateTotalPriceWithDiscount() {
        double hargaPakaianDenganDiskon = Laundry.calculateTotalPrice("Clothes", 5) * (1 - 0.1);
        assertEquals(22.5, hargaPakaianDenganDiskon, 0.01);
    }

    @Test
    public void testInvalidWeight() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Laundry.calculateTotalPrice("Clothes", -5));
        assertEquals("Weight must be greater than 0", exception.getMessage());
    }

    @Test
    public void testSaveToFile() {
        DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Name", "Laundry Type", "Weight", "Price","Estimated Time (hrs)", "Image Path"}, 0);
        tableModel.addRow(new Object[] {"Agus", "Clothes", 5, "$25.00","5" , "C:\\Users\\ASUS TUFF\\OneDrive\\Pictures\\Screenshot\\Screenshot 2024-10-26 193752.png"});

        Laundry.saveToFile(tableModel);

        File file = new File("receipt.html");
        assertTrue(file.exists(), "File receipt.html harus ada setelah disimpan");
    }
}


