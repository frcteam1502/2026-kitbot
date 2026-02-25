package frc.robot.subsystems;

import java.util.List;

    public final class LookupTable{
        
        public double[] inputs;
        public double[] outputs;
        public List<LookupTablePoint> m_points;

        public static final class LookupTablePoint{

            double m_distance; // feet
            double m_velocity; //rpm

            public LookupTablePoint(double feet, double revolutionsPerMinute){
                this.m_distance = feet;
                this.m_velocity = revolutionsPerMinute;
            }
            
        }

        public static final List<LookupTablePoint> LOOKUP_TABLE = List.of(
            //dummy points

            new LookupTablePoint(0, 0),
            new LookupTablePoint(1, 1),
            new LookupTablePoint(2, 2),
            new LookupTablePoint(3, 3),
            new LookupTablePoint(4, 4),
            new LookupTablePoint(5, 5),
            new LookupTablePoint(6, 6),
            new LookupTablePoint(7, 7)

            //must be ordered least to greatest
            //must have more than 2
            //must not repeat x values, aka distance
            );
            
            /**make inputs and outputs to interpolate between */
        public LookupTable(List<LookupTablePoint> points){
            
            //(untested)
            inputs = new double[points.size()];
            outputs = new double[points.size()];
            m_points = points;

            for (int i = 0; i < points.size(); i++){

                double x = points.get(i).m_distance;
                double y = points.get(i).m_velocity;

                inputs[i] = x;
                outputs[i] = y;
            }

            
            System.out.println(inputs);
            System.out.println(outputs);
        }
        public double interpolate(double distance){
            
            double x_greater;
            double y_greater;

            double x_less;
            double y_less;

            double m;

            double velocity = 0;

            for(int i = 0; i < m_points.size(); i++){
                
                if (inputs[i] > distance && i !=0){
                    x_greater = inputs[i];
                    y_greater = outputs[i];
                    
                    x_less = inputs[i-1];
                    y_less = outputs[i-1];

                    m = (y_greater-y_less)/(x_greater-x_less);
                    velocity = distance * m;
                }
            }

            return velocity;

        }
    }
