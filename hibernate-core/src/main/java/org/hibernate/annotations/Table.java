/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.annotations;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Complementary information for a table declared using the {@link jakarta.persistence.Table},
 * or {@link jakarta.persistence.SecondaryTable} annotation. Usually used only for secondary
 * tables.
 *
 * @author Emmanuel Bernard
 *
 * @see jakarta.persistence.Table
 * @see jakarta.persistence.SecondaryTable
 */
@Target(TYPE)
@Retention(RUNTIME)
@Repeatable(Tables.class)
public @interface Table {
	/**
	 * The name of the targeted table.
	 */
	String appliesTo();

	/**
	 * Indexes.
	 *
	 * @deprecated use {@link jakarta.persistence.Table#indexes()} or
	 *             {@link jakarta.persistence.SecondaryTable#indexes()}
	 */
	@Deprecated(since = "6.0")
	Index[] indexes() default {};

	/**
	 * A check constraint, written in native SQL.
	 * <p>
	 * <em>Useful for secondary tables, otherwise use {@link Check}.</em>
	 *
	 * @see Check
	 */
	String checkConstraint() default "";

	/**
	 * Specifies comment to add to the generated DDL for the table.
	 * <p>
	 * <em>Useful for secondary tables, otherwise use {@link Comment}.</em>
	 *
	 * @see Comment
	 */
	String comment() default "";

	/**
	 * Specifies a foreign key of a secondary table, which points back to the primary table.
	 *
	 * @deprecated use {@link jakarta.persistence.SecondaryTable#foreignKey()}
	 */
	@Deprecated(since = "6.0")
	ForeignKey foreignKey() default @ForeignKey( name="" );

	/**
	 * @deprecated This setting has no effect in Hibernate 6
	 */
	@Deprecated(since = "6.2")
	FetchMode fetch() default FetchMode.JOIN;

	/**
	 * If enabled, Hibernate will never insert or update the columns of the secondary table.
	 * <p>
	 * <em>Only applies to secondary tables.</em>
	 *
	 * @deprecated use {@link SecondaryRow#owned()}
	 */
	@Deprecated(since = "6.2")
	boolean inverse() default false;

	/**
	 * If enabled, Hibernate will insert a row only if the columns of the secondary table
	 * would not all be null, and will always use an outer join to read the columns. Thus,
	 * by default, Hibernate avoids creating a row of null values.
	 * <p>
	 * <em>Only applies to secondary tables.</em>
	 *
	 * @deprecated use {@link SecondaryRow#optional()}
	 */
	@Deprecated(since = "6.2")
	boolean optional() default true;

	/**
	 * Defines a custom SQL insert statement.
	 * <p>
	 * <em>Only applies to secondary tables.</em>
	 *
	 * @see SQLInsert
	 */
	SQLInsert sqlInsert() default @SQLInsert(sql="");

	/**
	 * Defines a custom SQL update statement.
	 * <p>
	 * <em>Only applies to secondary tables.</em>
	 *
	 * @see SQLUpdate
	 */
	SQLUpdate sqlUpdate() default @SQLUpdate(sql="");

	/**
	 * Defines a custom SQL delete statement.
	 * <p>
	 * <em>Only applies to secondary tables.</em>
	 *
	 * @see SQLDelete
	 */
	SQLDelete sqlDelete() default @SQLDelete(sql="");
}
