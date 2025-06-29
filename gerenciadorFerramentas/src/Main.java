//Eu decidi não mexer com a classe 'main' para não gerar problemas que eu não saberia resolver.
//A função principal que eu vou estar usando para explicar as funcionalidades desse aplicativo é a LaunchPage, e qualquer coisa nova vai ser explicada no local onde foi implementada.
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Main inicializado");
        LaunchPage launchPage = new LaunchPage(); //A verdadeira página incial é gerada através do construtor da mesma página, que está na sua mesma classe. Essa é a forma que eu decidi estruturar esse aplicativo.
    }
}
