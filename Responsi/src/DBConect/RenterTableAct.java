/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConect;

import java.util.List;
import model.Renter;

/**
 *
 * @author Lenovo
 */
public interface RenterTableAct {
    public List<Renter> getRenterList();
    public void updateData(Renter renter);
    public void addData(Renter renter);
    public void deleteData(String id);
    public void updateStatus(String id);
}
