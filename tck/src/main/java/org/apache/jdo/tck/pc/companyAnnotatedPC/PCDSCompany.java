/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jdo.tck.pc.companyAnnotatedPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PersistenceModifier;
import javax.jdo.annotations.Persistent;
import org.apache.jdo.tck.pc.company.IAddress;
import org.apache.jdo.tck.pc.company.ICompany;
import org.apache.jdo.tck.util.DeepEquality;
import org.apache.jdo.tck.util.EqualityHelper;

/** This class represents information about a company. */
@PersistenceCapable(table = "companies")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME, column = "DISCRIMINATOR")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "DATASTORE_IDENTITY")
public class PCDSCompany implements ICompany, Serializable, Comparable, Comparator, DeepEquality {

  @NotPersistent() private long _companyid;
  @NotPersistent() private String _name;
  @NotPersistent() private Date _founded;
  @NotPersistent() private PCDSAddress _address;
  @NotPersistent() private transient Set _departments = new HashSet();

  protected static SimpleDateFormat formatter = new SimpleDateFormat("d/MMM/yyyy");

  /**
   * This is the JDO-required no-args constructor. The TCK relies on this constructor for testing
   * PersistenceManager.newInstance(PCClass).
   */
  public PCDSCompany() {}

  /**
   * Initialize the <code>PCDSCompany</code> instance.
   *
   * @param companyid The company id.
   * @param name The company name.
   * @param founded The date the company was founded.
   */
  public PCDSCompany(long companyid, String name, Date founded) {
    this._companyid = companyid;
    this._name = name;
    this._founded = founded;
  }

  /**
   * Initialize the <code>Company</code> instance.
   *
   * @param companyid The company id.
   * @param name The company name.
   * @param founded The date the company was founded.
   * @param addr The company's address.
   */
  public PCDSCompany(long companyid, String name, Date founded, IAddress addr) {
    this(companyid, name, founded);
    this._address = (PCDSAddress) addr;
  }

  /**
   * Get the company id.
   *
   * @return The company id.
   */
  @Column(name = "ID")
  public long getCompanyid() {
    return _companyid;
  }

  /**
   * Set the id associated with this object.
   *
   * @param id the id.
   */
  public void setCompanyid(long id) {
    this._companyid = id;
  }

  /**
   * Get the name of the company.
   *
   * @return The name of the company.
   */
  @Column(name = "NAME", jdbcType = "VARCHAR")
  public String getName() {
    return _name;
  }

  /**
   * Set the name of the company.
   *
   * @param name The value to use for the name of the company.
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * Get the date that the company was founded.
   *
   * @return The date the company was founded.
   */
  @Column(name = "FOUNDEDDATE")
  public Date getFounded() {
    return _founded;
  }

  /**
   * Set the date that the company was founded.
   *
   * @param founded The date to set that the company was founded.
   */
  public void setFounded(Date founded) {
    this._founded = founded;
  }

  /**
   * Get the address of the company.
   *
   * @return The primary address of the company.
   */
  @Persistent(
      persistenceModifier = PersistenceModifier.PERSISTENT,
      types = org.apache.jdo.tck.pc.companyAnnotatedPC.PCDSAddress.class)
  @Embedded(
      nullIndicatorColumn = "COUNTRY",
      members = {
        @Persistent(name = "addrid", columns = @Column(name = "ADDRID")),
        @Persistent(name = "street", columns = @Column(name = "STREET")),
        @Persistent(name = "city", columns = @Column(name = "CITY")),
        @Persistent(name = "state", columns = @Column(name = "STATE")),
        @Persistent(name = "zipcode", columns = @Column(name = "ZIPCODE")),
        @Persistent(name = "country", columns = @Column(name = "COUNTRY"))
      })
  public IAddress getAddress() {
    return _address;
  }

  /**
   * Set the primary address for the company.
   *
   * @param address The address to set for the company.
   */
  public void setAddress(IAddress address) {
    this._address = (PCDSAddress) address;
  }

  /**
   * Get the departments contained in the company.
   *
   * @return All the <code>PCDSDepartment</code>s of the company.
   */
  @Persistent(persistenceModifier = PersistenceModifier.PERSISTENT, mappedBy = "company")
  @Element(types = org.apache.jdo.tck.pc.companyAnnotatedPC.PCDSDepartment.class)
  public Set getDepartments() {
    return _departments;
  }

  /**
   * Add a <code>PCDSDepartment</code> instance to the company.
   *
   * @param dept The <code>PCDSDepartment</code> instance to add.
   */
  public void addDepartment(PCDSDepartment dept) {
    _departments.add(dept);
  }

  /**
   * Remove a <code>PCDSDepartment</code> instance from the company.
   *
   * @param dept The <code>PCDSDepartment</code> instance to remove.
   */
  public void removeDepartment(PCDSDepartment dept) {
    _departments.remove(dept);
  }

  /**
   * Initialize the set of <code>PCDSDepartment</code>s in the company to the parameter.
   *
   * @param departments The set of <code>PCDSDepartment</code>s for the company.
   */
  public void setDepartments(Set departments) {
    // workaround: create a new HashSet, because fostore does not
    // support LinkedHashSet
    this._departments = (departments != null) ? new HashSet(departments) : null;
  }

  /**
   * Serialization support: initialize transient fields.
   *
   * @param in stream
   * @throws IOException error during reading
   * @throws ClassNotFoundException class could not be found
   */
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    _departments = new HashSet();
  }

  /**
   * Returns a String representation of a <code>Company</code> object.
   *
   * @return a String representation of a <code>Company</code> object.
   */
  public String toString() {
    return "Company(" + getFieldRepr() + ")";
  }

  /**
   * Returns a String representation of the non-relationship fields.
   *
   * @return a String representation of the non-relationship fields.
   */
  protected String getFieldRepr() {
    StringBuffer rc = new StringBuffer();
    rc.append(_companyid);
    rc.append(", name ").append(_name);
    rc.append(", founded ").append(_founded == null ? "null" : formatter.format(_founded));
    return rc.toString();
  }

  /**
   * Returns <code>true</code> if all the fields of this instance are deep equal to the coresponding
   * fields of the specified Person.
   *
   * @param other the object with which to compare.
   * @param helper EqualityHelper to keep track of instances that have already been processed.
   * @return <code>true</code> if all the fields are deep equal; <code>false</code> otherwise.
   * @throws ClassCastException if the specified instances' type prevents it from being compared to
   *     this instance.
   */
  public boolean deepCompareFields(Object other, EqualityHelper helper) {
    PCDSCompany otherCompany = (PCDSCompany) other;
    String where = "Company<" + _companyid + ">";
    return helper.equals(_companyid, otherCompany.getCompanyid(), where + ".companyid")
        & helper.equals(_name, otherCompany.getName(), where + ".name")
        & helper.equals(_founded, otherCompany.getFounded(), where + ".founded")
        & helper.deepEquals(_address, otherCompany.getAddress(), where + ".address")
        & helper.deepEquals(_departments, otherCompany.getDepartments(), where + ".departments");
  }

  /**
   * Compares this object with the specified object for order. Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified object.
   *
   * @param o The Object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   *     or greater than the specified object.
   * @throws ClassCastException - if the specified object's type prevents it from being compared to
   *     this Object.
   */
  public int compareTo(Object o) {
    return compareTo((PCDSCompany) o);
  }

  /** Compare two instances. This is a method in Comparator. */
  public int compare(Object o1, Object o2) {
    return compare((PCDSCompany) o1, (PCDSCompany) o2);
  }

  /**
   * Compares this object with the specified Company object for order. Returns a negative integer,
   * zero, or a positive integer as this object is less than, equal to, or greater than the
   * specified object.
   *
   * @param other The Company object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   *     or greater than the specified Company object.
   */
  public int compareTo(PCDSCompany other) {
    return compare(this, other);
  }

  /**
   * Compares its two ICompany arguments for order. Returns a negative integer, zero, or a positive
   * integer as the first argument is less than, equal to, or greater than the second.
   *
   * @param o1 the first ICompany object to be compared.
   * @param o2 the second ICompany object to be compared.
   * @return a negative integer, zero, or a positive integer as the first object is less than, equal
   *     to, or greater than the second object.
   */
  public static int compare(PCDSCompany o1, PCDSCompany o2) {
    return EqualityHelper.compare(o1.getCompanyid(), o2.getCompanyid());
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param obj the object with which to compare.
   * @return <code>true</code> if this object is the same as the obj argument; <code>false</code>
   *     otherwise.
   */
  public boolean equals(Object obj) {
    if (obj instanceof PCDSCompany) {
      return compareTo((PCDSCompany) obj) == 0;
    }
    return false;
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return a hash code value for this object.
   */
  public int hashCode() {
    return (int) _companyid;
  }

  /**
   * The class to be used as the application identifier for the <code>Company</code> class. It
   * consists of both the company name and the date that the company was founded.
   */
  public static class Oid implements Serializable, Comparable {

    /**
     * This field is part of the identifier and should match in name and type with a field in the
     * <code>Company</code> class.
     */
    public long companyid;

    /** The required public no-arg constructor. */
    public Oid() {}

    /**
     * Initialize the identifier.
     *
     * @param companyid The id of the company.
     */
    public Oid(long companyid) {
      this.companyid = companyid;
    }

    public Oid(String s) {
      companyid = Long.parseLong(justTheId(s));
    }

    public String toString() {
      return this.getClass().getName() + ": " + companyid;
    }

    /** */
    public boolean equals(Object obj) {
      if (obj == null || !this.getClass().equals(obj.getClass())) return false;
      Oid o = (Oid) obj;
      if (this.companyid != o.companyid) return false;
      return true;
    }

    /** */
    public int hashCode() {
      return (int) companyid;
    }

    protected static String justTheId(String str) {
      return str.substring(str.indexOf(':') + 1);
    }

    /** */
    public int compareTo(Object obj) {
      // may throw ClassCastException which the user must handle
      Oid other = (Oid) obj;
      if (companyid < other.companyid) return -1;
      if (companyid > other.companyid) return 1;
      return 0;
    }
  }
}
