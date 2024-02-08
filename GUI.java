import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.util.Objects;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Creates a graphing calculator within the bounds of x: {-2, 2} and y: {-2, 2}.
 * @author Arnab Utsa
 * 
 */



public class GUI {
	
	/**
	 * Stores the functions of the calculator
	 * 
	 * @param frmGraphingCalculator
	 */
	
	private JFrame frmGraphingCalculator;
	
	/**
	 * Input for double m
	 * 
	 * @param slopeField 
	 */
	
	private JTextField slopeField;
	
	/**
	 * Input for double b
	 * 
	 * @param offsetField 
	 */
	
	 private JTextField offsetField;
	 
	/**
	 * Button for linear function
	 * 
	 * @param lineX
	 */
	 
	private JRadioButton lineX;
	
	/**
	 * Button for quadratic function
	 * 
	 * @param squareX - Button for quadratic function
	 */
	
	private JRadioButton squareX;
	
	/**
	 * Button for logarithmic function
	 * 
	 * @param longX 
	 */
	
	private JRadioButton logX;
	
	/**
	 * Button for cubic function
	 * 
	 * @param cubeX 
	 */
	
	private JRadioButton cubeX;
	
	/**
	 * Button for radical function
	 * 
	 * @param sqrtX 
	 */
	
	private JRadioButton sqrtX;
	
	/**
	 * Double value for y-intercept
	 * 
	 * @param b 
	 */
	
	static double b;
	
	/**
	 * Double value for slope
	 * 
	 * @param m 
	 */
	
	static double m;
	
	/**
	 * Default function - x
	 * 
	 * @param selectedFunction 
	 */
	
    private String selectedFunction = "X"; 
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI window = new GUI();
                window.frmGraphingCalculator.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    
    public GUI() {
        initialize();
    }
    
    /**
     * This method will be called to create the Graphing Calculator application
     */

    private void initialize() {
        frmGraphingCalculator = new JFrame();
        frmGraphingCalculator.setTitle("Graphing Calculator");
        frmGraphingCalculator.setBounds(100, 100, 576, 578);
        frmGraphingCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmGraphingCalculator.getContentPane().setLayout(null);
        frmGraphingCalculator.setResizable(false);
        frmGraphingCalculator.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Banan\\Downloads\\graph.png"));//change the icon of frame
        frmGraphingCalculator.setContentPane(new JPanel() {
           
        	/**
             * Create Graphing Calculator's background
             * 
             * @param color1 - Color object used to get Light Sky Blue color 
             * @param color2 - Color object used to get Steel Blue color
             * @param gp - Gradient object used to merge the 2 colors
             */
        	
        	@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(48, 197, 210);  // Light Sky Blue
                Color color2 = new Color(0, 255, 135);   // Steel Blue
                GradientPaint gp = new GradientPaint(11, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });
        frmGraphingCalculator.getContentPane().setLayout(null);
        frmGraphingCalculator.setResizable(false);

        /**
         * Get slope input from user
         * 
         * @return Scalar (m): - Text label for slope
         */
        
        JLabel slope = new JLabel("Scalar (m):");
        slope.setFont(new Font("Comic Sans", Font.BOLD, 11));
        slope.setBounds(6, 14, 79, 14);
        frmGraphingCalculator.getContentPane().add(slope);

        slopeField = new JTextField();
        slopeField.addContainerListener(new ContainerAdapter() {});
        slopeField.setBounds(85, 11, 86, 20);
        frmGraphingCalculator.getContentPane().add(slopeField);
        slopeField.setColumns(10);

        /**
         * Get offset input from user
         * 
         * @return Offset (b): - Text label for y-intercept/offset
         */
        
        JLabel offset = new JLabel("Offset (b):");
        offset.setFont(new Font("Comic Sans", Font.BOLD, 11));
        offset.setBounds(363, 14, 60, 14);
        frmGraphingCalculator.getContentPane().add(offset);

        offsetField = new JTextField();
        offsetField.setColumns(10);
        offsetField.setBounds(433, 11, 86, 20);
        frmGraphingCalculator.getContentPane().add(offsetField);

        /**
         * Create graphing area
         * 
         * @param g - Graphics object used to draw functions
         * @param g2d - Graphics2D object used to increase dot thickness
         */
        
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            	Graphics2D g2d = (Graphics2D) g;
                super.paintComponent(g2d);
                drawGrid(g2d);

                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(3));

                if (selectedFunction.equals("X")){
                    drawX(g);// graphs slope
                } else if (Objects.equals(selectedFunction, "X^2")) {
                    drawSquareX(g);//graphs parabola
                } else if (Objects.equals(selectedFunction, "X^3") ){
                    drawCubeX(g);
                } else if (Objects.equals(selectedFunction, "Sqrt(X)")) {
                    drawSqrtX(g);
                } else if (Objects.equals(selectedFunction, "Log(X)")){
                    drawLogX(g);
                }else
                {
                    drawX(g);//if none is selected automatically prints x

                }

            }
            
            /**
             * This method will draw a linear function
             * 
             * @return Draws a linear function
             */
            
            private void drawX(Graphics g) {
            	Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.RED);
                double dx = 0.001;

                for (double x = -2; x <= 2; x += dx) {
                    double y = m * x + b;
                    int cX = (int) ((x + 2) * 100);
                    int cY = (int) ((2 - y) * 100);
                        g.drawLine(cX, cY, cX, cY);
                }
            }

            /**
             * This method will draw a cubic function
             * 
             * @return Draws cubic function
             */
            
            private void drawCubeX(Graphics g) {
                g.setColor(Color.RED);
                double dx = 0.001;
                for (double x = -2; x <= 2; x += dx) {
                    double y = m * x * x * x + b;
                    int cX = (int) ((x + 2) * 100);
                    int cY = (int) ((2 - y) * 100);
                    g.drawLine(cX, cY, cX, cY);
                }
            }
            
            /**
             * This method will draw a quadratic function
             * 
             * @return Draws quadratic function
             */
            
            private void drawSquareX(Graphics g) {
                g.setColor(Color.RED);
                double dx = 0.001;
                for (double x = -2; x <= 2; x += dx) {
                    double y = m * x * x + b;
                    int cX = (int) ((x + 2) * 100);
                    int cY = (int) ((2 - y) * 100);
                    g.drawLine(cX, cY, cX, cY);
                }
            }
            
            /**
             * This method will draw a logarithmic function
             * 
             * @return Draws logarithmic function
             */
            
            private void drawLogX(Graphics g) {
                g.setColor(Color.RED);
                double dx = 0.001;
                for (double x = -2; x <= 2; x += dx) {
                	if (x >= 0) {
                    double y = m * Math.log(Math.abs(x)) + b;
                    int cX = (int) ((x + 2) * 100);
                    int cY = (int) ((2 - y) * 100);
                    g.drawLine(cX, cY, cX, cY);
                	}
                }
            }
            
            /**
             * This method will draw a radical function
             * 
             * @return Draws radical function
             */
            
            private void drawSqrtX(Graphics g) {
                g.setColor(Color.RED);
                double dx = 0.001;
                for (double x = -2; x <= 2; x += dx) {
                	if (x >= 0) {
                    double y =  m * Math.sqrt(Math.abs(x)) + b;
                    int cX = (int) ((x + 2) * 100);
                    int cY = (int) ((2 - y) * 100);
                    g.drawLine(cX, cY, cX, cY);
                	}
                }
            }

            /**
             * This method will draw the grid of the canvas
             * 
             * @return Draws grid lines
             */
            
            private void drawGrid(Graphics g) {
            	g.setColor(Color.black);
    			g.drawString("x", 20, 190);
    			g.drawString("y", 180, 20);
    			g.drawString("1.0", 210, 105);
    			g.drawString("-1.0", 210, 305);
    			g.drawLine(200, 0, 200, 400);
    			g.drawLine(0, 200, 400, 200);
    			g.drawLine(195, 300, 205, 300);
    			g.drawLine(195, 100, 205, 100);
    			g.drawLine(100, 195, 100, 205);
    			g.drawString("-1", 95, 225);
    			g.drawLine(300, 195, 300, 205);
    			g.drawString("1", 298, 225);
            }
        };
        canvas.setBounds(80, 128, 400, 400);
        canvas.setBackground(Color.white);
        frmGraphingCalculator.getContentPane().add(canvas);
        canvas.setLayout(new SpringLayout());

        /**
         * This creates text for the linear function button
         * 
         * @return X
         */
        
        lineX = new JRadioButton("X");
        lineX.setFont(new Font("Comic Sans", Font.BOLD, 11));
        lineX.setBounds(6, 35, 109, 23);
        frmGraphingCalculator.getContentPane().add(lineX);

        /**
         * This creates text for the quadratic function button
         * 
         * @return X^2
         */
        
        squareX = new JRadioButton("X^2");
        squareX.setFont(new Font("Comic Sans", Font.BOLD, 11));
        squareX.setBounds(6, 61, 109, 23);
        frmGraphingCalculator.getContentPane().add(squareX);
        
        /**
         * This creates text for the logarithmic function button
         * 
         * @return Log(X)
         */

        logX = new JRadioButton("Log(X)");
        logX.setFont(new Font("TComic Sans", Font.BOLD, 11));
        logX.setBounds(6, 85, 109, 23);
        frmGraphingCalculator.getContentPane().add(logX);
        
        /**
         * This creates text for the cubic function button
         * 
         * @return X^3
         */

        cubeX = new JRadioButton("X^3");
        cubeX.setFont(new Font("Comic Sans", Font.BOLD, 11));
        cubeX.setBounds(142, 61, 109, 23);
        frmGraphingCalculator.getContentPane().add(cubeX);

        /**
         * This creates text for the radical function button
         * 
         * @return Sqrt(X)
         */
        
        sqrtX = new JRadioButton("Sqrt(X)");
        sqrtX.setFont(new Font("Comic Sans", Font.BOLD, 11));
        sqrtX.setBounds(142, 85, 109, 23);
        frmGraphingCalculator.getContentPane().add(sqrtX);
        
        /**
         * Assign functions to group
         * 
         * @param lineX - adds linear function to group
         * @param squareX - adds quadratic function to group
         * @param cubeX - adds cubic function to group
         * @param sqrtX - adds radical function to group
         * @param logX - adds logarithmic function to group
         */
        
        ButtonGroup functionGroup = new ButtonGroup();
        functionGroup.add(lineX);
        functionGroup.add(squareX);
        functionGroup.add(cubeX);
        functionGroup.add(sqrtX);
        functionGroup.add(logX);

        /**
         *  Displays slope intercept form text
         *  
         *  @return y = m(x) + b
         */
        
        JLabel formula = new JLabel("y = m(x) + b");
        formula.setFont(new Font("Comic Sans", Font.BOLD, 13));
        formula.setBounds(381, 61, 99, 23);
        frmGraphingCalculator.getContentPane().add(formula);

        /**
         * Creates graph button used to draw functions
         * 
         * Implements {@link #isNumeric() isNumeric} method to check if inputs are numbers
         * 
         * @param m - Double value for slope 
         * @param b - Double value for y - intercept/offset
         */
        
        JButton graph = new JButton("Graph");
        graph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m = isNumeric(slopeField.getText()) ? Double.parseDouble(slopeField.getText()) : 0;
                b = isNumeric(offsetField.getText()) ? Double.parseDouble(offsetField.getText()) : 0;
                selectedFunction = getSelectedFunction();
                canvas.repaint();
            }
        });
        graph.setFont(new Font("Comic Sans", Font.BOLD, 11));
        graph.setBounds(357, 81, 162, 23);
        frmGraphingCalculator.getContentPane().add(graph);
    }
    
    /**
     * Checks to see if a function is selected
     * 
     * @return X - linear function (Default function)
     * @return X^2 - quadratic function
     * @return X^3 - cubic function
     * @return Sqrt(X) - radical function
     * @return Log(X) - logarithmic function
     */
    
    private String getSelectedFunction() {
        if (lineX.isSelected()) {
            return "X";
        } else if (squareX.isSelected()) {
            return "X^2";
        } else if (cubeX.isSelected()) {
            return "X^3";
        } else if (sqrtX.isSelected()) {
            return "Sqrt(X)";
        } else if (logX.isSelected()) {
            return "Log(X)";
        } else {
            return "X"; 
        }
    }
    
/**
 * Checks to see if inputs are numbers
 * 
 * @param string - String input 
 * @return true - Number can be parsed as a valid floating point number | false - Number can't be parsed as a valid floating point
 * 
 */
    
    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}