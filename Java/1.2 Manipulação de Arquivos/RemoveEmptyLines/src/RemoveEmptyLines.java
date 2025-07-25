import java.io.BufferedReader;  
import java.io.BufferedWriter; 
import java.io.FileReader;  
import java.io.FileWriter; 
import java.io.IOException;  

public class RemoveEmptyLines {

    public static void main(String[] args) {
        String inputFile = "input.txt"; //Busca-se na raiz do projeto
        String outputFile = "output.txt"; //Salva-se na raiz do projeto
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            
            reader.close();
            writer.close();

            System.out.println("O conteúdo foi processado com sucesso!");

        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao processar os arquivos: " + e.getMessage());
        }
    }
}
