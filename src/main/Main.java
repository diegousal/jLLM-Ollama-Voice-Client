package main;
import controller.Controller;
import model.ILLM;
import model.IRepository;
import model.JSONRepository;
import model.LLMCSV;
import model.LLMFake;
import model.LLMSmart;
import model.Model;
import model.XMLRepository;
import view.ApplicationView;
import view.SimpleConsoleView;
import view.VoiceConsoleView;

public class Main{

    public static void main(String args[]){

        IRepository repository;
        ApplicationView view;
        ILLM LLM;

        if(args.length!=3){
            
           view=new SimpleConsoleView();
           repository=new XMLRepository();
           LLM=new LLMFake();

        }else{
                repository=getRepository(args[0]);
                LLM=getLLM(args[1]);
                view=getView(args[2]);
              }
        Model model=new Model(repository, LLM);
        Controller c= new Controller(model, view);
        c.initApplication();
        }

    private static ILLM getLLM(String arg) {
                switch (arg) {
            case "fake":
                return new LLMFake();                
            case "csv":
                return new LLMCSV();
            case "smart":
                return new LLMSmart();
            default:
                return new LLMFake();
                
        }
    }

    private static IRepository getRepository(String arg) {
                switch (arg) {
            case "xml":
                return new XMLRepository();                
            case "json":
                return new JSONRepository();
            default:
                return new XMLRepository();
                
        }

       
    }

    private static ApplicationView getView(String arg) {
        switch (arg) {
            case "consola":
                return new SimpleConsoleView();                
            case "voz":
                return new VoiceConsoleView();
            default:
                return new SimpleConsoleView();
                
        }
    }
        
    
    
}