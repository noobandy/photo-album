package in.anandm.pa.web.controllers;

import in.anandm.pa.dao.PhotoDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mongodb.DBObject;

@RestController
public class PhotoController {

    @Autowired
    private PhotoDAO photoDAO;

    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    public List<DBObject> getMessages() {
        return photoDAO.findAllPhotos();
    }

    @RequestMapping(value = "/photos", method = RequestMethod.POST)
    public ResponseEntity<String> postMessage(MultipartHttpServletRequest request) {
        photoDAO.uploadPhoto(request.getFile("photo"));
        return new ResponseEntity<String>(HttpStatus.OK);

    }
}
