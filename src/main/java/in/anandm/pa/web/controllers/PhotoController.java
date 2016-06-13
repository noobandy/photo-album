package in.anandm.pa.web.controllers;

import in.anandm.pa.dao.PhotoDAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@RestController
public class PhotoController {

    @Autowired
    private PhotoDAO photoDAO;

    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    public List<DBObject> getPhotos() {
        return photoDAO.findAllPhotos();
    }

    @RequestMapping(value = "/photos", method = RequestMethod.POST)
    public ResponseEntity<String> postPhoto(MultipartHttpServletRequest request) {

        photoDAO.uploadPhoto(request.getFile("photo"));
        return new ResponseEntity<String>(HttpStatus.OK);

    }

    @RequestMapping(value = "/photos/{id}/{name}", method = RequestMethod.GET)
    public void getPhotoData(@PathVariable ObjectId id,
                             @PathVariable String name,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        GridFSDBFile file = photoDAO.findPhoto(id);

        if (file != null) {
            response.setContentType(file.getContentType());
            response.setContentLength((int) file.getLength());
            file.writeTo(response.getOutputStream());
        }
        else {
            response.sendError(404);
        }

    }
}
