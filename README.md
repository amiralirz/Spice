# Spice
• Independent voltage source
v<name> <+ terminal> <- terminal> <value>

• Independent current source
i<name> <+ terminal> <- terminal> <value>

• Voltage-controlled voltage source
E<name> <+ terminal> <- terminal> <+ control> <- control> <gain>

• Current-controlled voltage source (vcontrol refers to the voltage source which the controlling current
flows through)
H<name> <+ terminal> <- terminal> <vcontrol> <gain>
• Voltage-controlled current source

G<name> <+ terminal> <- terminal> <+ control> <- control> <gain>
• Current-controlled current source (vcontrol refers to the voltage source which the controlling current
flows through)
F<name> <+ terminal> <- terminal> <vcontrol> <gain>

• Sinusoidal source (used as a <value>)
sin(<offset> <amplitude> <frequency> <delay> <damping> <phase>)

• Square wave source (used as a <value>)
pulse(<vmin> <vmax> <delay> <rise time> <fall time> <pulse width> <period>)

• Piece-wise linear source (used as a <value>)
pwl(<t0> <v0> <t1> <v1> <t2> <v2> ...)

• Resistor
r<name> <terminal 1> <terminal 2> <value>

• Capacitor
c<name> <terminal 1> <terminal 2> <value>

• Inductor
l<name> <terminal 1> <terminal 2> <value>

• Diode
d<name> <+ terminal> <- terminal> <model> <parameter list>

• AC analysis (pick either lin, dec, or oct)
.ac <lin|dec|oct|> <number of samples> <freq start> <freq stop>

• DC analysis
.dc <source> <start> <stop> <step>


5 SYNTAX REFERENCE 16

• Nested DC analysis (source1 is swept, source2 is stepped)
.dc <source1> <start1> <stop1> <step1> <source2> <start2> <stop2> <step2>

• Transient analysis
.tran <t step> <t stop>

• TF analysis
.tf v(<node>) <source>

• PZ analysis
.pz v(<node>) <source>
