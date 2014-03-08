import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

public class Create extends JFrame
{
  private final int size = 10;
  private final Cell[][] grid = new Cell[size][size];
  private final JFrame options;
  public Create()
  {
    super();
    this.setLayout(new GridLayout(size,size,1,1));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
    for (int y = 0; y < size; y++)
    {
      grid[y] = new Cell[size];
      for(int x = 0; x < size; x++)
      { 
        Cell cell = new Cell();
        this.add(cell);
        grid[y][x] = cell;
      }
    }

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        try{ saveToFile(new File("start")); }
        catch(IOException e2){ System.out.println("file save failed"); e2.printStackTrace(); }
      }
    });
    
    options = new JFrame();
    options.add(saveButton);
    options.pack();
    
    this.pack();
  }

  @Override
  public void setVisible(boolean visible)
  {
    super.setVisible(visible);
    options.setVisible(visible);
  }

  public void saveToFile(File output) throws IOException
  {
    PrintWriter writer = new PrintWriter(output);
    for(Cell[] row : grid)
    {
      for (Cell cell : row)
      {
        writer.printf("%s ",cell);
      }
      writer.print("\n");
    }
    writer.close();
  }


  public static void main(String args[])
  {
    new Create().setVisible(true);
  }

  public static class Cell extends JButton implements ActionListener
  {
    public State state = State.getDefault();
    public Cell()
    {
      super();
      this.setBorder(null);
      this.addActionListener(this);
      updateImage();
    }

    private void updateImage()
    {
      switch (state)
      {
        case DEAD:
          this.setText("-");
          break;
        case ALIVE:
          this.setText("#");
          break;
        default:
          this.setText("E");
      }
    }
    public String toString()
    {
      switch(state)
      {
        case DEAD: return "-";
        case ALIVE: return "#";
      }
      return "-";
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      state = state.getNext();
      updateImage();
    }
    
    @Override
    public Dimension getPreferredSize() {
      return new Dimension(30, 30);
    }

    public static enum State
    {
      DEAD,ALIVE;
      public static State getDefault(){ return DEAD; }
      public State getNext()
      {
        return this == DEAD? ALIVE:DEAD;
      }
    }
  }
}
