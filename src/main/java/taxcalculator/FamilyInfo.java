/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxcalculator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alvan
 */
public class FamilyInfo {
     private SpouseInfo spouse;
    private List<ChildInfo> children = new ArrayList<>();

    public void setSpouse(SpouseInfo spouse) {
        this.spouse = spouse;
    }

    public void addChild(ChildInfo child) {
        children.add(child);
    }

    public boolean hasSpouse() {
        return spouse != null;
    }

    public int getNumberOfChildren() {
        return Math.min(children.size(), 3);
    }
}
