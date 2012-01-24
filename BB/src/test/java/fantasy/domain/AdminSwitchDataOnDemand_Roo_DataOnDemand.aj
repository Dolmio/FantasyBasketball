// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fantasy.domain;

import fantasy.domain.AdminSwitch;
import java.lang.Boolean;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect AdminSwitchDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AdminSwitchDataOnDemand: @Component;
    
    private Random AdminSwitchDataOnDemand.rnd = new SecureRandom();
    
    private List<AdminSwitch> AdminSwitchDataOnDemand.data;
    
    public AdminSwitch AdminSwitchDataOnDemand.getNewTransientAdminSwitch(int index) {
        AdminSwitch obj = new AdminSwitch();
        setPositionsEditable(obj, index);
        return obj;
    }
    
    public void AdminSwitchDataOnDemand.setPositionsEditable(AdminSwitch obj, int index) {
        Boolean positionsEditable = false;
        obj.setPositionsEditable(positionsEditable);
    }
    
    public AdminSwitch AdminSwitchDataOnDemand.getSpecificAdminSwitch(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        AdminSwitch obj = data.get(index);
        return AdminSwitch.findAdminSwitch(obj.getId());
    }
    
    public AdminSwitch AdminSwitchDataOnDemand.getRandomAdminSwitch() {
        init();
        AdminSwitch obj = data.get(rnd.nextInt(data.size()));
        return AdminSwitch.findAdminSwitch(obj.getId());
    }
    
    public boolean AdminSwitchDataOnDemand.modifyAdminSwitch(AdminSwitch obj) {
        return false;
    }
    
    public void AdminSwitchDataOnDemand.init() {
        data = AdminSwitch.findAdminSwitchEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'AdminSwitch' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<fantasy.domain.AdminSwitch>();
        for (int i = 0; i < 10; i++) {
            AdminSwitch obj = getNewTransientAdminSwitch(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
