import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestListener {


        private Frame mainFrame;
        private Label headerLabel;
        private Label statusLabel;
        private Panel controlPanel;

        public TestListener(){
            prepareGUI();
        }

        public static void main(String[] args){
            TestListener  awtListenerDemo = new TestListener();
            awtListenerDemo.showComponentListenerDemo();
        }

        private void prepareGUI(){
            mainFrame = new Frame("Java AWT Examples");
            mainFrame.setSize(400,400);
            mainFrame.setLayout(new GridLayout(3, 1));
            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                }
            });

            headerLabel = new Label();
            headerLabel.setAlignment(Label.CENTER);
            statusLabel = new Label();
            statusLabel.setAlignment(Label.CENTER);
            statusLabel.setSize(350,100);

            controlPanel = new Panel();
            controlPanel.setLayout(new FlowLayout());

            mainFrame.add(headerLabel);
            mainFrame.add(controlPanel);
            mainFrame.add(statusLabel);
            mainFrame.setVisible(true);
        }

        private void showComponentListenerDemo(){
            headerLabel.setText("Listener in action: ComponentListener");

            ScrollPane panel = new ScrollPane();
            panel.setBackground(Color.magenta);

            Label msglabel = new Label();
            msglabel.setAlignment(Label.CENTER);
            msglabel.setText("Welcome to TutorialsPoint AWT Tutorial.");
            panel.add(msglabel);

            msglabel.addComponentListener(new CustomComponentListener());
            controlPanel.add(panel);
            mainFrame.setVisible(true);
        }

        class CustomComponentListener implements ComponentListener {

            @Override
            public void componentResized(ComponentEvent e) {
                statusLabel.setText(statusLabel.getText()
                        + e.getComponent().getClass().getSimpleName() + " 调整大小 resized. ");
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                statusLabel.setText(statusLabel.getText()
                        + e.getComponent().getClass().getSimpleName() + " 移动moved. ");
            }

            @Override
            public void componentShown(ComponentEvent e) {
                statusLabel.setText(statusLabel.getText()
                        + e.getComponent().getClass().getSimpleName() + " shown. ");
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                statusLabel.setText(statusLabel.getText()
                        + e.getComponent().getClass().getSimpleName() + " hidden. ");
            }
        }}



