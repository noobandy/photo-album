package in.anandm.pa.dao;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class PhotoDAO {

    private GridFS photos;

    /**
     * @param photos
     */
    public PhotoDAO(GridFS photos) {
        super();
        this.photos = photos;
    }

    public void uploadPhoto(MultipartFile file) {
        try {
            GridFSInputFile photo = photos.createFile(
                    file.getInputStream(), file.getOriginalFilename(), true);
            photo.put("contentType", file.getContentType());
            photo.save();
        }
        catch (IOException e) {

            new RuntimeException(e);
        }

    }

    public List<DBObject> findAllPhotos() {

        return photos.getFileList().toArray();
    }

    public GridFSDBFile findPhoto(ObjectId id) {

        return photos.findOne(id);
    }
    /*
     * public static void main(String[] args) throws IOException { MongoClient
     * client = new MongoClient(); GridFS photos = new
     * GridFS(client.getDB("photo-album"), "photos");
     * 
     * String filename = "picnic.jpeg";
     * 
     * File file = new File("E:\\desk\\nasik_trip\\DSC_0041.jpg"); InputStream
     * in = new FileInputStream(file);
     * 
     * GridFSInputFile picnicPhoto = photos.createFile(in);
     * 
     * picnicPhoto.setFilename(filename);
     * 
     * picnicPhoto.save();
     * 
     * GridFSDBFile gridFSDBFile = photos.findOne(filename);
     * 
     * gridFSDBFile.writeTo(filename);
     * 
     * photos.remove(filename);
     * 
     * client.close(); }
     */
}
