package ru.test;

import java.util.Set;

import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class ShTimerProcessor implements Processor{

	@Override
	public Set<String> getSupportedOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ProcessingEnvironment processingEnv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<? extends Completion> getCompletions(Element element,
			AnnotationMirror annotation, ExecutableElement member,
			String userText) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static long value = 0;
	public void process(){
    	try {
    		// sleep randomly current thread for some seconds - just emulate real situation
  		    Thread.sleep( Math.round( Math.random() * 3000 ) );
  	    } catch (InterruptedException e) {
  		    e.printStackTrace();  
  	    }
        System.out.println( "fired generator times:" + value++ + "" );
	}

}
