package com.faost.security.service.misc;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by faos7 on 29.12.16.
 */

@Service
public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static int getNumber(int[] canvas){
        while (true){
            try {
                int n = Integer.parseInt(reader.readLine());
                if (n >= 0 && n < canvas.length && canvas[n]==0){
                    return n;
                }
                System.out.println("Choose free cell and enter its number");
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number");
            } catch (IOException e) {
            }
        }
    }
}
