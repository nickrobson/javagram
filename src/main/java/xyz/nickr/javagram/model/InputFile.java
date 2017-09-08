package xyz.nickr.javagram.model;

import java.io.InputStream;
import lombok.Data;

/**
 * @author Nick Robson
 */
@Data
public class InputFile {

    private final InputStream inputStream;
    private final String name;

}
