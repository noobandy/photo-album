package in.anandm.pa.web.security.jwt;

public class JwtTokenMissingException extends Exception {

    /**
     * @param string
     */
    public JwtTokenMissingException(String string) {
        super(string);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
