package edu.cmart.util.method;

import edu.cmart.entity.Role;
import edu.cmart.entity.enums.TypeRoles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleValueIndex {

    public static List<Integer> getIndex(Set<Role> roles) {
        List<Integer> index = new ArrayList<>();
        for (Role role : roles) {
            index.add(role.getTypeRoles().ordinal());
        }
        return index;
    }

    public static Set<Role> getRoles(List<Integer> index) {
        Set<Role> roles = new HashSet<>();
        for (int i = 0; i < index.size(); i++) {
            Role role = new Role();
            role.setTypeRoles(TypeRoles.values()[index.get(i)]);
            roles.add(role);
        }
        return roles;
    }

}
