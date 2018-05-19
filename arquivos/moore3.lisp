(moore
(symbols-in a b c)
(symbols-out 0 1)
(states q0 q1 q2 q3)
(start q0)
(finals q3)
(trans
(q0 q1 a) (q0 q2 b) (q0 q0 c) (q1 q1 a)
(q1 q2 b) (q1 q3 c) (q2 q0 a) (q2 q3 b)
(q2 q3 c) (q3 q3 a) (q3 q3 b) (q3 q3 c))
(out-fn
(q0 1) (q1 1) (q2 1)
(q3 0)))