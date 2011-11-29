import java.awt.Color;
import java.awt.Component.*;
import java.awt.Dimension;
import javax.swing.InputMap.*;
import javax.swing.ActionMap.*;
import javax.swing.*;
import java.awt.Font;

import java.awt.event.*;

class Play implements Runnable
{
    // Menu Components
    private JFrame menu;
    private javax.swing.JButton startbutton;
    private javax.swing.JButton instructionbutton;
    private javax.swing.JButton instructionbutton1;
    private javax.swing.JButton instructionbutton2;
    private javax.swing.JLabel bg;
    
    // Game Components
    private JFrame w;
    private Ian ian;
    private Erik erik;
    private Rubbish rubbish1;
    private Rubbish rubbish2;
    private Rubbish rubbish3;
    private RubbishMaster master;
    private Page page;
    private Bogdan bogdan;
    private Speech speech;
    private ImageIcon pic;
    private JLabel label;
    private IntroThread intro;
    private Bin overflow;
    private Explosion explosion;
    private EndGame endgame;
    private Cleaner cleaner;
    private dbJLabel points;
    private dbJLabel time;
    private dbJLabel itemsLeft;
    private dbJLabel dblabel;
    private int timeSecs = 0;
    private int timeMins = 2;
    private int rubbishLeft = 40;
    private int pointsStore = 0;
    private int comboStore = 0;
    private Boolean keepTiming = true;
    private Boolean keepingDancing = true;
    private TimeThread timethread;

    public static void main(String[] args)
    {
        Play program = new Play();
        SwingUtilities.invokeLater(program);
    }

    // Create menu
    public void run()
    {
        menu = new JFrame();
        menu.setLocationByPlatform(true);
        menu.setLayout(null);
        menu.setTitle("Postlash Panic");
        menu.setVisible(true);
        menu.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);

        startbutton = new javax.swing.JButton();
        instructionbutton = new javax.swing.JButton();
        instructionbutton1 = new javax.swing.JButton();
        instructionbutton2 = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        // Create the start button
        startbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/startbut.png")));
        startbutton.setBorderPainted(false);
        startbutton.setContentAreaFilled(false);
        startbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startbuttonActionPerformed(evt);
            }
        });
        menu.add(startbutton);
        startbutton.setBounds(10, 410, 370, 80);

        // Create the "instructions" button
        instructionbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/instbut.png")));
        instructionbutton.setBorderPainted(false);
        instructionbutton.setContentAreaFilled(false);
        instructionbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionbuttonActionPerformed(evt);
            }
        });
        menu.add(instructionbutton);
        instructionbutton.setBounds(10, 500, 370, 90);

        // Create the button to go back to the menu from the instruction screen, but hide it
        instructionbutton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/inst2button.png")));
        instructionbutton2.setBorderPainted(false);
        instructionbutton2.setContentAreaFilled(false);
        instructionbutton2.setVisible(false);
        menu.add(instructionbutton2);
        instructionbutton2.setBounds(637, 462, 150, 130);
        instructionbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionbutton2ActionPerformed(evt);
            }
        });

        // Create the button to go to the second instruction screen, but hide it
        instructionbutton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/inst1button.png")));
        instructionbutton1.setBorderPainted(false);
        instructionbutton1.setContentAreaFilled(false);
        instructionbutton1.setVisible(false);
        instructionbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionbutton1ActionPerformed(evt);
            }
        });
        menu.add(instructionbutton1);
        instructionbutton1.setBounds(630, 420, 140, 160);

        // Set up the background of the menu screen
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/menu.png")));
        bg.setOpaque(true);
        menu.add(bg);
        bg.setBounds(0, 0, 800, 600);

        menu.pack();
        menu.setSize(800, 625);
        menu.setResizable(false);
    }

    // When the start button is pressed, launch the game frame and hide other
    // buttons
    private void startbuttonActionPerformed(java.awt.event.ActionEvent evt) {
        menu.setVisible(false);
        runGame();
        startbutton.setVisible(false);
        instructionbutton.setVisible(false);
    }

    // When the instructions button is pressed, change the background to the
    // instruction panel and hide irrelevant buttons
    private void instructionbuttonActionPerformed(java.awt.event.ActionEvent evt) {
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/inst1.png")));
        startbutton.setVisible(false);
        instructionbutton1.setVisible(true);
        instructionbutton.setVisible(false);
    }

    // When the second instruction button is pressed, change the background to
    // the second instruction screen
    private void instructionbutton1ActionPerformed(java.awt.event.ActionEvent evt) {
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/inst2.png")));
        instructionbutton1.setVisible(false);
        instructionbutton2.setVisible(true);
    }

    // When the third instruction button is pressed, change the background back
    // to the menu
    private void instructionbutton2ActionPerformed(java.awt.event.ActionEvent evt) {
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/UI/menu.png")));
        instructionbutton2.setVisible(false);
        startbutton.setVisible(true);
        instructionbutton.setVisible(true);
    }

    // Create the main window
    public void runGame()
    {
        w = new JFrame();

        w.addKeyListener(kl);
        w.setDefaultCloseOperation(w.EXIT_ON_CLOSE);
        w.setTitle("Postlash Panic");
        w.setFocusable(true);
        w.setLayout(null);
        w.setLocationByPlatform(true);
        w.setVisible(true);
        // When the game has ended, display the "endgame" graphic depending
        // on the conditions.
        endgame = new EndGame( this );
        w.add(endgame);

        // Display an explosion if the player attempts to add more to a full bin
        explosion = new Explosion();
        explosion.addKeyListener(kl);
        w.add(explosion);

        speech = new Speech();
        speech.addKeyListener(kl);
        w.add(speech);

        // Three items of trash are allowed on screen at once, and are kept
        // track of here
        rubbish1 = new Rubbish( this );
        rubbish1.addKeyListener(kl);
        w.add(rubbish1);

        rubbish2 = new Rubbish( this );
        rubbish2.addKeyListener(kl);
        w.add(rubbish2);

        rubbish3 = new Rubbish( this );
        rubbish3.addKeyListener(kl);
        w.add(rubbish3);

        master = new RubbishMaster( rubbish1, rubbish2, rubbish3 );

        overflow = new Bin( this, w );

        // Make a new instance of each of the four characters
        bogdan = new Bogdan( this );
        bogdan.addKeyListener(kl);
        w.add(bogdan);

        page = new Page( this );
        page.addKeyListener(kl);
        w.add(page);

        ian = new Ian( this );
        ian.addKeyListener(kl);
        w.add(ian);

        erik = new Erik( this );
        erik.addKeyListener(kl);
        w.add(erik);

        cleaner = new Cleaner();
        cleaner.addKeyListener(kl);
        w.add(cleaner);

        points = new dbJLabel(pointsStore+"", JLabel.CENTER);
        points.setSize(new Dimension(165,56));
        points.setForeground(Color.white);
        points.setFont(new Font("Tahoma", Font.PLAIN, 48));
        points.setLocation(123, 70);
        w.add(points);

        time = new dbJLabel("", JLabel.CENTER);
        time.setSize(new Dimension(165,56));
        time.setForeground(Color.white);
        time.setFont(new Font("Tahoma", Font.PLAIN, 48));
        time.setLocation(303, 70);
        displayTime();
        w.add(time);

        itemsLeft = new dbJLabel(rubbishLeft+"", JLabel.CENTER);
        itemsLeft.setSize(new Dimension(165,56));
        itemsLeft.setForeground(Color.white);
        itemsLeft.setFont(new Font("Tahoma", Font.PLAIN, 48));
        itemsLeft.setLocation(482, 70);
        w.add(itemsLeft);

        //ImageDisplay background = new ImageDisplay();
        bg.setIcon(new ImageIcon(getClass().getResource("img/UI/bg2.png")));
        w.add(bg);

        // Structure how the components are layered
        w.setComponentZOrder( endgame, new Integer( 0 ) );
        w.setComponentZOrder( speech, new Integer( 1 ) );
        w.setComponentZOrder( explosion, new Integer( 2 ) );
        w.setComponentZOrder( rubbish1, new Integer( 3 ) );
        w.setComponentZOrder( rubbish2, new Integer( 4 ) );
        w.setComponentZOrder( rubbish3, new Integer( 5 ) );
        w.setComponentZOrder( ian, new Integer( 6 ) );
        w.setComponentZOrder( page, new Integer( 7 ) );
        w.setComponentZOrder( erik, new Integer( 8 ) );
        w.setComponentZOrder( bogdan, new Integer( 9 ) );
        w.setComponentZOrder( cleaner, new Integer( 10 ) );
        w.setComponentZOrder( points, new Integer( 11 ));
        w.setComponentZOrder( time, new Integer( 12 ) );
        w.setComponentZOrder( itemsLeft, new Integer( 13 ) );

        overflow.zOrder( 14 );
        w.setComponentZOrder( bg , new Integer( 19 ) );

        w.pack();
        w.setSize(800, 625);
        
        
        w.setResizable(false);

        intro = new IntroThread(erik, ian, speech, this);
        intro.start();
        bg.repaint();
    }

    // Determine when the game ends (when the clock runs out)
    public void addTime()
    {
        if ( timeSecs == 0 )
        {
            if ( timeMins == 0 )
            {
                keepTiming = false;
                endgame.GameLose();
            }else{
                timeSecs = 59;
                timeMins--;
            }
        }
        else
        {
            timeSecs--;
        }

        displayTime();
    }

    // Display the clock
    private void displayTime()
    {
        String text = timeMins + ":";
        if ( timeSecs < 10 )
            time.setText(text + "0" + timeSecs);
        else
            time.setText(text + timeSecs);
    }

    // Update the items left counter, if there is none left, the player wins
    public void takeRubbish( int num )
    {
        rubbishLeft -= num;
        itemsLeft.setText( rubbishLeft + "" );

        if ( rubbishLeft == 0 )
            endgame.GameWin();
    }

    // Control the points counter, adding them in relation to the combo
    // multiplier
    public void addPoints()
    {
        comboStore += 10;
        pointsStore += comboStore;
        points.setText( pointsStore + "" );
    }

    // After an unsuccessful throw, the combo multiplier resets to 0
    public void resetCombo()
    {
        comboStore = 0;
    }


    public Boolean keepTiming()
    {
        return keepTiming;
    }

    public void startTiming()
    {
        timethread = new TimeThread( this );
        timethread.start();
    }

    public Explosion getExplosion()
    {
        return explosion;
    }

    public Bogdan getBogdan()
    {
        return bogdan;
    }

    public Ian getIan()
    {
        return ian;
    }

    public Cleaner getCleaner()
    {
            return cleaner;
    }

    public Erik getErik()
    {
        return erik;
    }

    public Page getPage()
    {
        return page;
    }

    public RubbishMaster getMaster()
    {
        return master;
    }

    public Bin getOverflow()
    {
        return overflow;
    }

    public Speech getSpeech()
    {
        return speech;
    }

    public void setKeepTiming( Boolean bo )
    {
        keepTiming = bo;
    }

    // Monitor key presses
    public KeyListener kl=new KeyAdapter()
    {
        public void keyPressed(KeyEvent evt)
        {
            ian.keyPressed( evt.getKeyChar() );
        }
        public void keyReleased(KeyEvent e){}
        public void keyTyped(KeyEvent e) {}
    };
}