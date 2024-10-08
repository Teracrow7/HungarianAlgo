package invetigaciónDeOperaciones;
import javax.swing.table.DefaultTableCellRenderer;      
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



import javax.swing.*;





public class Menu {
	JFrame frameFinal = new JFrame();

	ImageIcon iconTec = new ImageIcon("tec.png");
	JLabel labelAssignment = new JLabel();
	JPanel panel = new JPanel();

	DefaultTableModel model3 = new DefaultTableModel();
	JTable table3 = new JTable(model3);;

	DefaultTableModel model2 = new DefaultTableModel();
	JTable table2 = new JTable(model2);;
	


	 private TableDarkCell cell;
	 private TableDarkHeader header;
	JLabel labelRows = new JLabel("Rows");
	JLabel labelColumns = new JLabel("Columns");
	int[][] tableData;
	DefaultTableModel model = new DefaultTableModel();
	JTable table = new JTable(model);;
	int rows;
	int columns;
	JTextField rowsTextField = new JTextField();
	JTextField columnsTextField = new JTextField();
	ArrayList<JLabel> results = new ArrayList<JLabel>();
	
	JFrame menuFrame = new JFrame();
	void createPrincipalFrame(){
		
		
		frameFinal.add(table3);
		
	
		
		menuFrame.setIconImage(iconTec.getImage());
		labelRows.setBounds(180, 525, 70, 20);
		labelRows.setForeground(Color.white);
		labelColumns.setBounds(280, 525, 70, 20);
		labelColumns.setForeground(Color.white);
	
		menuFrame.add(panel);
		menuFrame.add(new CalculateButton());
		rowsTextField.setBounds(180, 500, 70, 20);
		columnsTextField.setBounds(280, 500, 70, 20);
		menuFrame.setTitle("TecNM");
		menuFrame.add(labelRows);
		menuFrame.add(labelColumns);
		menuFrame.add(new createRowsxColumns());
		menuFrame.add(rowsTextField);
		menuFrame.add(columnsTextField);
	
		//menuFrame.add(table3);
		menuFrame.add(table2);
		menuFrame.add(table);
		menuFrame.setBackground(new Color(30,30,30));
		menuFrame.add(new PanelBG());
		
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setSize(1300,600);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setVisible(true);
	}
	
	
	public void createTable(int rows2, int columns2) {
		
	
		 cell = new TableDarkCell();
		 header = new TableDarkHeader();
		 table.getTableHeader().setDefaultRenderer(header);
	     table.getTableHeader().setPreferredSize(new Dimension(0, 35));
	     
		
		model.setNumRows(rows2);
		model.setColumnCount(columns2);
		table.setDefaultRenderer(Object.class, cell);
		
		table.setBounds(150, 100, columns2*80, rows2*20);
		table.setRowHeight(20);
		table.setGridColor(new Color(200,200,200));
		table.setForeground(new Color(200, 200, 200));
		table.setBackground(new Color(30,30,30));
		
		
		table.setVisible(true);
		
	
		
	}
	private class TableDarkCell extends DefaultTableCellRenderer {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Map<Integer, Integer> alignment = new HashMap<>();

        @SuppressWarnings("unused")
		public void setAlignment(int column, int align) {
            alignment.put(column, align);
        }

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
            if (table.isCellSelected(row, column)) {
                if (row % 2 == 0) {
                    com.setBackground(new Color(33, 103, 153));
                } else {
                    com.setBackground(new Color(29, 86, 127));
                }
            } else {
                if (row % 2 == 0) {
                    com.setBackground(new Color(50, 50, 50));
                } else {
                    com.setBackground(new Color(30, 30, 30));
                }
            }
            com.setForeground(new Color(200, 200, 200));
            com.setFont(new Font("Arial",Font.PLAIN,18));
            setBorder(new EmptyBorder(0, 5, 0, 5));
            if (alignment.containsKey(column)) {
                setHorizontalAlignment(alignment.get(column));
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }
            return com;
        }

		
    }
	public class  createRowsxColumns extends JButton implements ActionListener{
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Color color1 = new Color(32, 0, 44);
		 private Color color2 = new Color(203, 180, 212);
		createRowsxColumns(){
			this.setBounds(20, 500, 120, 25);
			this.addActionListener(this);
			this.setText("Update Table");
			this.setForeground(Color.white);
			this.setFocusable(false);
			this.setContentAreaFilled(false);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.setBorder(new EmptyBorder(10,20,10,20));
		}

		public void paint(Graphics g) {
			
			int Height = getHeight();
			int Width = getWidth();
			BufferedImage img = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2D = img.createGraphics();
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			GradientPaint gp = new GradientPaint(0,0,color1,Width,0,color2);
			g2D.setPaint(gp);
			g2D.fillRoundRect(0, 0, Width, Height, Height, Height);
			g.drawImage(img,0,0,null);
			
			super.paintComponent(g);
			
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this) {
				 rows =Integer.valueOf(rowsTextField.getText());
				columns = Integer.valueOf(columnsTextField.getText());
				createTable(rows,columns);
			}
			
		}
	}
	
	class PanelBG extends JPanel{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Color color1 = new Color(30, 30, 30);
		private Color color2 = new Color(30, 30, 30);
		
		public PanelBG(){
			
			this.setPreferredSize(new Dimension(420,420));
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			
			Graphics2D g2D = (Graphics2D) g;
			
			int h = getHeight();
			int w = getWidth();
			
			GradientPaint gp = new GradientPaint(0,0,color1,0,h,color2);
			
			g2D.setPaint(gp);
			g2D.fillRect(0, 0, w, h);
			
			
		}
		
		

	}
	
	public void findMinValue() {
		
		int rows=table.getRowCount();
		int columns=table.getColumnCount();
		
		if(rows!=columns) {
			if(rows>columns) {
				int difference=rows-columns;
				columns+=difference;
				createTable(rows,columns);
				
			}else
			if(columns>rows) {
				int difference=columns-rows;
				rows+=difference;
				createTable(rows,columns);
			}
		}else {
		tableData  = new int[rows][columns];
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				if(table.getValueAt(i, j)==null) {
					tableData[i][j] =0;
				}else {
				  tableData[i][j] = Integer.valueOf((String) table.getValueAt(i,j));
				}
			}
		}
		
		}
		
		
		
		
		 int finalData[][]= new int[rows][columns];
	      for(int i=0;i<tableData.length;i++) {
	    	  for(int j=0;j<tableData[i].length;j++) {
	    		  finalData[i][j]=tableData[i][j];
	    	  }
	      }
	      // rows
	        for (int i = 0; i < tableData.length; i++) {
	            // find the minimum value of the current row
	            int currentRowMin = Integer.MAX_VALUE;
	            for (int j = 0; j < tableData[i].length; j++) {
	                if (tableData[i][j] < currentRowMin) {
	                    currentRowMin = tableData[i][j];
	                }
	            }
	            // subtract min value from each element of the current row
	            for (int k = 0; k < tableData[i].length; k++) {
	            	tableData[i][k] -= currentRowMin;
	            }
	        }

	        // columns
	        for (int i = 0; i < tableData[0].length; i++) {
	            // find the min value of the current column
	            int currentColMin = Integer.MAX_VALUE;
	            for (int j = 0; j < tableData.length; j++) {
	                if (tableData[j][i] < currentColMin) {
	                    currentColMin = tableData[j][i];
	                }
	            }
	            // subtract min value from each element of the current column
	            for (int k = 0; k < tableData.length; k++) {
	            	tableData[k][i] -= currentColMin;
	            }
	        }
	        create2ndTable(rows,columns,tableData);
	      //find optimal assignment
	      HungarianSolver HungarianLol = new HungarianSolver(tableData);
	      int[][] assignment = HungarianLol.findOptimalAssignment();
	    printResults(rows,columns,assignment,finalData);
	      /*for(int i=0;i<rows;i++) {
	    	  JLabel finalLabel = new JLabel("Col" + assignment[i][0] + " => Row" + assignment[i][1] + " (" + finalData[assignment[i][0]][assignment[i][1]] + ")");
	    	  finalLabel.setForeground(Color.white);
	    	  finalLabel.setBounds(40, xLabel, 150, 30);
	    	  menuFrame.add(finalLabel);
	    	  xLabel=+100;
	      }*/
	     
	    
	    
		
		

		
	}
	
	public void create2ndTable(int rows2, int columns2,int newTable[][]) {
		
		int x=table.getBounds().x;
		int y=table.getBounds().y;
		x+=table.getWidth();
		//y+=table.getHeight();
		
		 cell = new TableDarkCell();
		 header = new TableDarkHeader();
		table2.getTableHeader().setDefaultRenderer(header);
	    table2.getTableHeader().setPreferredSize(new Dimension(0, 35));
	     
		
		model2.setNumRows(rows2);
		model2.setColumnCount(columns2);
		table2.setDefaultRenderer(Object.class, cell);
		
		table2.setBounds(x+150, y, rows2*80, columns2*20);
		table2.setRowHeight(20);
		table2.setGridColor(new Color(200,200,200));
		table2.setForeground(new Color(200, 200, 200));
		table2.setBackground(new Color(30,30,30));

		
		for(int i=0;i<newTable.length;i++) {
			for(int j=0;j<newTable[i].length;j++) {
				table2.setValueAt(newTable[i][j], i, j);
			}
		}
		
		
		table2.setVisible(true);
		//System.out.println("hola");
	}
	
	public void printResults(int rows,int columns,int assignment[][], int[][] finalData2){ 
		//panel.setSize(300,300);
		panel.setBounds(30,300,200,200);
		panel.setBackground(new Color(30, 30, 30));
	int y=10;
	int totalCost=0;		
	if (assignment.length > 0) {
		 for (int i = 0; i < assignment.length; i++) { 
			 JLabel resultado = new JLabel("Fila" + assignment[i][1] + " => Columna" + assignment[i][0] + " (" + finalData2[assignment[i][1]][assignment[i][0]] + ")");
			resultado.setFont(new Font("Arial",Font.PLAIN,18));
			 resultado.setForeground(Color.white);
			 resultado.setBounds(30, y, 100, 40);
			 resultado.setVisible(true);
			 panel.add(resultado);
			 totalCost+=finalData2[assignment[i][1]][assignment[i][0]];
			 y+=50;
		 }
	}
	
		JLabel costoTotal = new JLabel( "El Costo total es de: "+totalCost);
		costoTotal.setFont(new Font("Arial", Font.PLAIN,18));
		costoTotal.setForeground(Color.white);
		costoTotal.setBounds(30, y, 100, 40);
		costoTotal.setVisible(true);
		panel.add(costoTotal);
		panel.setVisible(true);
		
		
	     
	  
	       
	    
	      
	}
	public class CalculateButton extends JButton implements ActionListener{
		
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Color color1 = new Color(32, 0, 44);
		 private Color color2 = new Color(203, 180, 212);
		 CalculateButton(){
			this.setBounds(390, 500, 100, 25);
			this.addActionListener(this);
			this.setText("Calculate");
			this.setForeground(Color.white);
			this.setFocusable(false);
			this.setContentAreaFilled(false);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.setBorder(new EmptyBorder(10,20,10,20));
		}

		public void paint(Graphics g) {
			
			int Height = getHeight();
			int Width = getWidth();
			BufferedImage img = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2D = img.createGraphics();
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			GradientPaint gp = new GradientPaint(0,0,color1,Width,0,color2);
			g2D.setPaint(gp);
			g2D.fillRoundRect(0, 0, Width, Height, Height, Height);
			g.drawImage(img,0,0,null);
			
			super.paintComponent(g);
			
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this) {
				findMinValue();
			}
			
		}
		}
		
	
	 @SuppressWarnings("serial")
	private class TableDarkHeader extends DefaultTableCellRenderer {

	        private Map<Integer, Integer> alignment = new HashMap<>();

	        @Override
	        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
	            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
	            com.setBackground(new Color(30, 30, 30));
	            com.setForeground(new Color(200, 200, 200));
	            com.setFont(com.getFont().deriveFont(Font.BOLD, 12));
	            if (alignment.containsKey(i1)) {
	                setHorizontalAlignment(alignment.get(i1));
	            } else {
	                setHorizontalAlignment(JLabel.LEFT);
	            }
	            return com;
	        }
	    }

	 
	public void create3rdTable(int rows3,int columns3,int[][] thirdTable) {
		int x=table2.getBounds().x;
		int y=table2.getBounds().y;
		x+=table.getWidth();
		frameFinal.setSize(300,300);
		frameFinal.setBounds(x+735,y+400,rows3*80, columns3*20);
		frameFinal.setBackground(new Color(30,30,30));
		//frameFinal.setLocationRelativeTo(menuFrame);
		frameFinal.setUndecorated(true);
	
		
		 cell = new TableDarkCell();
		 header = new TableDarkHeader();
		table3.getTableHeader().setDefaultRenderer(header);
	    table3.getTableHeader().setPreferredSize(new Dimension(0, 35));
	     
		
		model3.setNumRows(rows3);
		model3.setColumnCount(columns3);
		table3.setDefaultRenderer(Object.class, cell);
		
		table3.setBounds(10, 10, rows3*80, columns3*20);
		table3.setRowHeight(20);
		table3.setGridColor(new Color(200,200,200));
		table3.setForeground(new Color(200, 200, 200));
		table3.setBackground(new Color(30,30,30));

		
		for(int i=0;i<thirdTable.length;i++) {
			for(int j=0;j<thirdTable[i].length;j++) {
				System.out.print(thirdTable[i][j]+" ");
				table3.setValueAt(thirdTable[i][j], i, j);
			}
			System.out.println(" ");
		}
		frameFinal.add(table3);
		table3.setVisible(true);
		System.out.println("Hola");
		frameFinal.setVisible(true);
		
	}
	
}



	
