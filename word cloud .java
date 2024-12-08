import javax.swing.*; 
import java.awt.*; 
import java.awt.font.FontRenderContext; 
import java.awt.geom.Rectangle2D; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner; 
public class NameShapeFiller { 
public static void main(String[] args) { 
Scanner scanner = new Scanner(System.in); 
System.out.println("Enter names to fill the text shape (type 'done' to finish):"); 
List<String> innerNamesList = new ArrayList<>(); 
while (true) { 
String name = scanner.nextLine(); 
if (name.equalsIgnoreCase("done")) break; 
innerNamesList.add(name); 
} 
if (innerNamesList.isEmpty()) { 
System.out.println("No names entered. Exiting..."); 
return; 
} 
String[] innerNames = innerNamesList.toArray(new String[0]); 
JFrame frame = new JFrame("Names Forming 'SHAKILA'"); 
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
frame.setSize(800, 600); 
frame.add(new NamePanel("SHAKILA", innerNames)); 
frame.setVisible(true); 
} 
} 
class NamePanel extends JPanel { 
private final String mainName;  
private final String[] innerNames; 
private int nameIndex = 0; 
public NamePanel(String mainName, String[] innerNames) { 
this.mainName = mainName; 
this.innerNames = innerNames; 
} 
@Override 
protected void paintComponent(Graphics g) { 
super.paintComponent(g); 
Graphics2D g2d = (Graphics2D) g; 
g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
 
Font mainFont = new Font("Serif", Font.BOLD, 200); 
g2d.setFont(mainFont); 
FontMetrics fm = g2d.getFontMetrics(mainFont); 
// Calculate position to center the main name 
int mainNameWidth = fm.stringWidth(mainName); 
int xCenter = (getWidth() - mainNameWidth) / 2; 
int yCenter = getHeight() / 2; 
FontRenderContext frc = g2d.getFontRenderContext(); 
Shape mainNameShape = mainFont.createGlyphVector(frc, mainName).getOutline(xCenter, yCenter); 
g2d.setColor(Color.GRAY); 
// Fill the main name shape with white 
g2d.fill(mainNameShape); 
g2d.setColor(Color.blue); 
int gridSize = 15;  
for (int x = 0; x < getWidth(); x += gridSize) { 
for (int y = 0; y < getHeight(); y += gridSize) { 
if (mainNameShape.contains(x, y)) { 
String currentName = innerNames[nameIndex]; 
g2d.setFont(new Font("SansSerif", Font.PLAIN, 10)); // Smaller font size for inner names 
g2d.drawString(currentName, x, y); 
nameIndex = (nameIndex + 1) % innerNames.length; 
} 
} 
} 
g2d.setColor(Color.LIGHT_GRAY); 
g2d.draw(mainNameShape); 
} 
} 
