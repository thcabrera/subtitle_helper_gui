// adds two seconds to Barbarian.srt and checks the output file

import grafica.Sincronizador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SrtSynchTest {

    @Test
    void synchSrt() throws IOException {
        String baseSrt = "src/test/resources/Barbarian.srt";
        String wellSynchronizedSrt = "src/test/resources/Barbarian_synch.srt";
        // TODO agregar al sincronizador la opcion de especificar nueva ubicacion
        // TODO asi después le pasamos la ruta de archivos temporales del so
        Sincronizador sincronizador = new Sincronizador(baseSrt);
        sincronizador.sincSRT(5000);
        String newSyncronizedSrt = sincronizador.obtenerNombre(1);
        sincronizador.cerrar();
        Path pathNewSyncronizedSrt = Paths.get(newSyncronizedSrt);
        Assertions.assertArrayEquals(Files.readAllBytes(pathNewSyncronizedSrt),
                Files.readAllBytes(Paths.get(wellSynchronizedSrt)));
        Files.delete(pathNewSyncronizedSrt);
    }

}
