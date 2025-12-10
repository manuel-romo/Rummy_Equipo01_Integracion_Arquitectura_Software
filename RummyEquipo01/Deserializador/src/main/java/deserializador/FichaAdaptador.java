
package deserializador;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dtos.FichaComodinDTO;
import dtos.FichaDTO;
import dtos.FichaNormalDTO;
import java.lang.reflect.Type;


public class FichaAdaptador implements JsonDeserializer<FichaDTO> {
        
        @Override
        public FichaDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
                throws JsonParseException {
            
            JsonObject jsonObject = json.getAsJsonObject();
            String tipo = jsonObject.get("tipo").getAsString();

            if ("Normal".equals(tipo)) {
                return context.deserialize(jsonObject, FichaNormalDTO.class);
            } else if ("Comodin".equals(tipo)) {
                return context.deserialize(jsonObject, FichaComodinDTO.class);
            }
            
            throw new JsonParseException("Tipo de ficha desconocido: " + tipo);
        }
    }
