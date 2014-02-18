package org.mule.modules.domino.util;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * <code>TransformUtil</code>
 */
public class TransformUtil {

    /**
     * Transform properties map to Domino document
     * @param database
     * @param source
     * @param target
     * @throws lotus.domino.NotesException
     */
    public static void mapToDocument(Database database, Map<String,Object> source, Document target) throws NotesException
    {

        for (Map.Entry<String, Object> item:source.entrySet())
        {
            String key = item.getKey();
            Object value = item.getValue();

            if (value instanceof Date)
            {
                value = database.getParent().createDateTime((Date)value);
            }
            else if (value instanceof Calendar)
            {
                value = database.getParent().createDateTime((Calendar)value);
            }

            if (target.getItemValue(key) == null)
                target.appendItemValue(key, value);
            else
                target.replaceItemValue(key, value);
        }
    }
}

