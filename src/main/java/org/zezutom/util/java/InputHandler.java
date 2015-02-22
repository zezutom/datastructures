package org.zezutom.util.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles console / file input
 */
public class InputHandler {

    public int[] readFromFile(String path) {
        List<Integer> numList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                numList.add(Integer.valueOf(br.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from " + path);
        }

        int[] nums = new int[numList.size()];

        for (int i = 0; i < numList.size(); i++) {
            nums[i] = numList.get(i);
        }
        return nums;
    }
}
