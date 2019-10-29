; Helper function - do not change
(define (mydisplay value)
	(display value)
	(newline)
	#t
)

; Returns the intersection of two sets. The inputs are flat lists
; of atoms. The result is a flat list containing all the elements
; that appear in both of the two lists. No duplicates 
; should be present in the result. Order is not important.
; You can use the built-in function member.
; (intersection '(a b c) '(1 2 a b c)) -> (a b c)
; (intersection '(a b c) '(1 2 a b c 0)) -> (a b c)
; (intersection '(a b c) '(1 2)) -> ()
(define (intersection lst1 lst2)
	(if (null? lst1) '()
        (let ((included (isElement (car lst1) lst2)))
			(if (null? (cdr lst1))
				(if included lst1 '())
				(if included
					(cons (car lst1) (intersection (cdr lst1) lst2))
					(intersection (cdr lst1) lst2)
				)
			)
		)
	)
)

; helper function. returns whether or not x is an element of the list lst
(define (isElement x lst)
	(cond
		((null? lst) #f)
        ((eq? x (car lst)) #t)
        (#t (isElement x (cdr lst)))
	)
)

; Return a list containing the original list's values multiplied by c.
; lst is a list of integers and c is an integer
(define (multiplyIt c lst)
	(multiplyItHelper c lst '())
)

; helper function so i can keep track of a new list
(define (multiplyItHelper c lst newList)
	(cond
		((null? lst) newList)
		(#t (multiplyItHelper c (cdr lst) (append newList (list (* (car lst) c)))))
	)
)


; Returns a list with only the positive items in lst
; For example (posOnly '(-1 1 2 0 3 4 -4 5)) should return
; (1 2 3 4 5)
(define (posOnly lst)
	(posOnlyHelper lst '())
)

; helper function so i can keep track of a new list
(define (posOnlyHelper lst newList)
	(cond
		((null? lst) newList)
		((> (car lst) 0) (posOnlyHelper (cdr lst) (append newList (list (car lst)))))
		(#t (posOnlyHelper (cdr lst) newList))
	)
)

; Returns a list of three numbers (numZero numPos numNeg),
; where these numbers correspond to the number of zeros,
; number of positive numbers, and the number of negative numbers
; respectively.
; For example (zeroPosNeg '(-9 2 3 0 -2 -8 0)) should return
; (2 2 3). Approximately, 20% of this problem's points will be
; awarded for doing this with just one pass through the list.
; lst -- flat list containing numeric values, and length is >= 1.
(define (zeroPosNeg lst)
	(zeroPosNegHelper lst 0 0 0)
)

; helper function so i can keep track of some variables
(define (zeroPosNegHelper lst numZ numP numN)
	(cond
		((null? lst) (list numZ numP numN))
		((zero? (car lst)) (zeroPosNegHelper (cdr lst) (+ numZ 1) numP numN))
		((> (car lst) 0) (zeroPosNegHelper (cdr lst) numZ (+ numP 1) NumN))
		((< (car lst) 0) (zeroPosNegHelper (cdr lst) numZ numP (+ numN 1)))
	)
)

; Removes all occurrences of v from lst,
; where v is an integer and lst is a list of integers.
; The function must use tail recursion!
; No points if tail recursion is not used!
(define (removeTail v lst)
	(removeTailHelper v lst '())
)

(define (removeTailHelper v lst newList)
	(cond
		((null? lst) newList)
		((eq? (car lst) v) (removeTailHelper v (cdr lst) newList))
		(#t (removeTailHelper v (cdr lst) (append newList (list (car lst)))))
	)
)

; sales.scm contains all the company's sales.
; You should not modify this file. Your code
; should work for other instances of this file
; such as salesBig.scm 
(load "sales.scm")
;(load "salesBig.scm")

; Returns the profit information out of a given record for a sale.
; (getProfit '(3 ("10/13/2010" "10/20/2010") (261.54 0.04 -213.25 38.94) ("Regular Air" "Nunavut") "Eldon Base for stackable storage shelf, platinum")) -> -213.25
(define (getProfit sale)
	(getListElement (getListElement sale 2) 2)
)

; helper function to return the element of a list at index i
(define (getListElement lst i)
	(cond
		((zero? i) (car lst))
		(#t (getListElement (cdr lst) (- i 1)))
	)
)

; Returns the province information out of a given record for a sale.
; (getProv '(3 ("10/13/2010" "10/20/2010") (261.54 0.04 -213.25 38.94) ("Regular Air" "Nunavut") "Eldon Base for stackable storage shelf, platinum")) -> Nunavut
(define (getProv sale)
	(getListElement (getListElement sale 3) 1)
)

; Returns the average profit (per items sold) for a given province. 
; Returned orders are not included in this calculation.
(define (averageProfitProv prov sales returns)
	(getAverage (getProfitList (getSalesInProv prov sales '()) '()) 0 0)
)

; returns the list of sales in province prov
(define (getSalesInProv prov sales newList)
	(cond
		((null? sales) newList)
		((equal? prov (getProv (car sales))) (getSalesInProv prov (cdr sales) (append newList (list (car sales)))))
		(#t (getSalesInProv prov (cdr sales) newList))
	)
)

; returns a list of profits given a list of sales
(define (getProfitList sales newList)
	(cond
		((null? sales) newList)
		(#t (getProfitList (cdr sales) (append newList (list (getProfit(car sales))))))
	)
)

; returns the average of a list of numbers
(define (getAverage lst sum count)
	(cond
		((null? lst) (/ sum count))
		(#t (getAverage (cdr lst) (+ sum (car lst)) (+ count 1)))
	)
)

; returns the date of a sale
(define (getDate sale)
	(getListElement (getListElement sale 1) 0)
)

; Returns the set (i.e., list with no duplicates) of order numbers
; that were placed on a given date.
(define (getOrdersPlacedOn date sales)
	(getOrdersPlacedOnHelper date sales '())
;	(getDate (car sales))
)

(define (getOrdersPlacedOnHelper date sales newList)
	(cond
		((null? sales) newList)
		((equal? date (getDate (car sales))) (getOrdersPlacedOn date (cdr sales) (append newList (list (car sales)))))
		(#t (getOrdersPlacedOn date (cdr sales) newList))
	)
)

; Returns the set (i.e., list with no duplicates) of order numbers
; with multiple items being ordered.
; Note that the records in sales are ordered by order number.
(define (multipleItems sales)
; TODO: Fill this in
	'()	; stub -- modify it accordingly
)

; Returns the list of provinces to which a given item was sold and the
; numbers of such items sold in that province.
; The expected result is a list of the form
; '((prov1 numItem1) (prov2 numItem2) ... (provn numItemn) 
; where numItemi is the number of items of the type given as an input sold in provi, 
; and prov1, prov2, ... provn are unique (no duplicates)
(define (getProvincesForItem item sales)
; TODO: Fill this in
	'((prov1 numItem1) (prov2 numItem2)) 	; stub -- modify it accordingly
)

; Do not modify the following line
(load "hw3tests.scm")

,exit
