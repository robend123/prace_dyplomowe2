/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Users;
import java.util.List;

/**
 *
 * @author Robson
 */
public interface IUserCRUDService {

    List<Users> findAllAdministrators();
    List<Users> findAllStudents();
    List<Users> findAllTeachers();
    void saveUser(Users user);
    void updateOneUser(Users userToUpdate);
    Users findUserByAlbumNumber(long albumNumber);
    public void deleteOneUser(Users preparedUser);
}
