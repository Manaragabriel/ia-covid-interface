/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projnava;
import java.awt.BorderLayout;
import java.util.*;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.classifiers.trees.J48;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.Filter;
import weka.attributeSelection.*;
import weka.core.Instance;
import weka.core.DenseInstance;
/**
 *
 * @author Manara
 */
public class WekaImp {
    
        
    	public WekaImp() {
		try {
                    DataSource file = new DataSource("C:/Users/Manara/Documents/java/covid.arff");
                    Instances data = file.getDataSet();
                    String[] parameters = new String[] {"-R","1"};
                    Remove filter = new Remove();
                    filter.setOptions(parameters);
                    filter.setInputFormat(data);
                    data = Filter.useFilter(data, filter);
			
		    AttributeSelection selAttribute = new AttributeSelection();
		    InfoGainAttributeEval avaliator = new InfoGainAttributeEval();
		    Ranker search = new Ranker();
		    
		    selAttribute.setEvaluator(avaliator);
		    selAttribute.setSearch(search);
		    selAttribute.SelectAttributes(data);
		    int[] indexes = selAttribute.selectedAttributes(); 
		    
		    String[] options = new String[1];
                    options[0] = "-U";
                    J48 three = new J48();
                    three.setOptions(options);
                    three.buildClassifier(data);

                    double[] values = new double[data.numAttributes()];

                    values[0] = 1.0;
                    values[7] = 1.0;

                    values[11] = 1.0;

                    Instance patient = new DenseInstance(1.0, values);

                    patient.setDataset(data);

                    double label = three.classifyInstance(patient);

                    System.out.println(data.classAttribute().value((int) label));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
        
        public String getDisease(double attributes[]){
            
            return "";
        }
}
