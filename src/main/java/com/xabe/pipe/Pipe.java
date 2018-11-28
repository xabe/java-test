package com.xabe.pipe;

import java.util.Objects;
import java.util.function.Function;

public class Pipe<FROM, TO> implements Function<FROM, TO> {
 
    final private Function<FROM, TO> function;
 
    private Pipe( final Function<FROM, TO> function ) {
        Objects.requireNonNull( function, "Function cannot be null" );
        this.function = function;
    }
 
    /**
     * Start creating a Pipe by providing first function.
     */
    final public static <FROM, TO> Pipe<FROM, TO> from( final Function<FROM, TO> function ) {
        return new Pipe<FROM, TO>( function );
    }
 
    /**
     * Here comes the funny part -- adding new function to the Pipe 
     * returns new Pipe instance with the target type set to the target
     * type of the function. Internal function wraps previous Pipe 
     * and the new function.
     */
    final public <NEXT> Pipe<FROM, NEXT> then( final Function<TO, NEXT> function ) {
        return new Pipe<FROM, NEXT>( new Function<FROM, NEXT>() {
            @Override
            public NEXT apply( final FROM from ) {
                final TO intermediate = Pipe.this.function.apply( from );
                return function.apply( intermediate );
            }
        } );
    }
 
    /**
     * All functions will be applied recursively.
     */
    @Override
    public TO apply( final FROM input ) {
        return this.function.apply( input );
    }
	

}
