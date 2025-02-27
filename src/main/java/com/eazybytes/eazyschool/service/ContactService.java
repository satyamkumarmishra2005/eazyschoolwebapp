package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Save Contact Details into DB
     *
     * @param contact
     * @return boolean
     */

    //In summary, this method saves a contact to the database, sets its status to "OPEN", creator to "ANONYMOUS", and creation date and time to the current date and time.
    // It then returns a boolean indicating whether the contact was successfully saved or not.
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
       Contact savedContact = contactRepository.save(contact);

        if(null!= savedContact && savedContact.getContactId()>0){
            isSaved = true;
        }
        return isSaved;
    }


    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }


    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        int rows = contactRepository.updateStatusById(EazySchoolConstants.CLOSE,contactId);
        if (rows > 0) {
            isUpdated= true;

        }
        return isUpdated;
    }


}

