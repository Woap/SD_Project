#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QCheckBox>
#include <fancyslider.h>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

    FancySlider *productor;
    FancySlider *productargent;
    FancySlider *productbronze;
    FancySlider *indi;
    FancySlider *coop;
    FancySlider *voleur;
    FancySlider *prudent;
    FancySlider *coopprudent;

    QCheckBox *ordo ;
    QCheckBox *humain ;
    QCheckBox *observation ;
    QCheckBox *epuisable ;
    QCheckBox *vol ;
    QCheckBox *fin ;

private slots:
    void changement(QWidget *ordo);

    void on_pushButton_clicked();

private:
    Ui::MainWindow *ui;



};

#endif // MAINWINDOW_H
